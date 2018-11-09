package simplex;
import Jama.Matrix;
import java.time.Clock;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class Simplex implements Solver{

    private static final int BIG_M = 1000000;
    
	private Model model;
        
        /**
         * matriz interna, solo variables y sin la columna y fila del Z, tampoco tiene la fila de las igualdades
         */
        private Matrix ConsLeft ;
        /**
         * matriz identidad antes de convertirse
         */
        private Matrix MId;
        /**
         * matriz con la función objetivo dada en la primera columna
         */
        private Matrix FObj;
        /**
         * matriz que representa las variables de holgura que acompañan a la función objetivo (primera columna)
         */
        private Matrix SlackOF;
        /**
         * matriz de igualdades en la primera columna
         */
        private Matrix equalities;
        /**
         * indices que forman la matriz identidad
         */
        private int [] Base;
        /**
         * matriz que representa la matriz final de una iteración (sin la columna del Z)
         */
        private Matrix Final;
        
        private int nVarDecision;
        
        public Simplex(String opti, String[] equations) {
        String [] caracteres = equations[0].split(" ");
//      -2 del 1 z y -2 del = C. No se divide entre 2 por las variables de holgura
        nVarDecision = caracteres.length/2-2;
        Variable[] vars = new Variable[nVarDecision];
        double [] pesos = new double[nVarDecision];
            for (int i = 0; i < nVarDecision; i++) {
                vars[i] = new Variable(caracteres[(i*2)+1], Variable.CONTINUOUS);
                pesos[i] = Double.parseDouble(caracteres[i*2]);
            }
//            for (int i = 1; i < equations.length; i++) {
//                caracteres = equations[i].split(" ");
////                -2 del 1 z y -2 del ><= Constante
//                Variable[] vars = new Variable[caracteres.length/2-2];
//                int j;
//                for (j = 0; j < caracteres.length-1; j++) {
//                    vars[j] = new Variable(caracteres[j*2+3], Integer.parseInt(caracteres[j*2+2]), true);
//                }
//                model.addConstraint(new Constraint(vars,caracteres[j+1]));
//            }
            if(Model.MAXIMIZE.equalsIgnoreCase(opti))
                model = new Model(vars, pesos, Model.MAXIMIZE);
            else model = new Model(vars, pesos, Model.MINIMIZE);
            generateEquaAndFOMatries(equations);
            generateConstraintsLeftMatrix(equations, model.getType().equals(Model.MAXIMIZE));
            calculateInitialBase();
            internalteration(model.getType().equals(Model.MAXIMIZE));
//            System.out.print(isMaximization);
        }
            
        /**
         * Cambia a la siguiente matriz de la iteración correspondiente (no contiene la columna del Z)
         * @return Una matriz completa que corresponde al resultado de la iteración
         */
        public double[][] nextIteration () {
            if(quotientTest(Final))
             internalteration(model.getType().equals(Model.MAXIMIZE));
            else if(isFactibleSolution())
             Final.print(2,2);
             return Final.getArray();
        }
        
        /**
         * Calcula la base inicial del problema
         */
       public void calculateInitialBase () {
           double[][] array = ConsLeft.getArray();
           Base = new int[equalities.getArray().length];
           for (int i = 0; i < array.length; i++) {
               for (int j = nVarDecision; j < array[0].length; j++) {
                   if(array[i][j] == 1) {
                       boolean base = true;
                       for (int k = 0; k < array.length; k++) {
                           if(array[k][j] != 0 && k != i){
                               base = false;
                               break;
                           }
                       }
                       if(base)
                           Base[i] = j;
                   }
               }
           }
           for (int i = 0; i < Base.length; i++) {
                System.out.println(Base[i]);
            }
       }
       
       /**
        * Crea una matriz que compone todas las matrices de entrada
        * @param TAB lado izquierdo de la igualdad
        * @param X_B don know
        * @param Fila_z ecuación objetivo
        * @param z_v don know
        * @return matriz completa dados los parámetros
        */
       public static Matrix CrearTabla (Matrix TAB, Matrix X_B, Matrix Fila_z, Matrix z_v){
 
             double[][] Tabla = new double [TAB.getRowDimension()+1][TAB.getColumnDimension()+1];
 
             for (int j = 0; j<TAB.getColumnDimension(); j++){
                    Tabla[0][j] = Fila_z.getArray()[0][j];                             
             }
             Tabla[0][TAB.getColumnDimension()] = z_v.getArray()[0][0];
 
             for (int i = 0; i<TAB.getRowDimension(); i++){
                    for (int j = 0; j<TAB.getColumnDimension(); j++){
                           Tabla[i+1][j] = TAB.getArray()[i][j];                              
                    }
             }
 
             for (int i = 0; i<TAB.getRowDimension(); i++){
                    Tabla[i+1][TAB.getColumnDimension()] = X_B.getArray()[i][0];                                         
             }
             Matrix Tabla1 = new Matrix(Tabla);
             return Tabla1;
       }
 
       /**
        * Crea la matriz don know
        * @param A
        * @param Base
        * @return 
        */
       public static Matrix CreaB(Matrix A, int [] Base){
 
             double [][] B1 = new double [Base.length][Base.length];
 
             for (int i =0; i<Base.length; i++){
                    for (int j =0; j<Base.length; j++){
                           B1[i][j] = A.getArray()[i][Base[j]];                                                   
                    }
             }
 
             Matrix B = new Matrix(B1);
//             System.out.println("B: ");
//             B.print(2, 2);
             return B;
       }
       public Matrix takeSlackOF(Matrix ObjF, int [] Base){
             double [][] C_B1 = new double [Base.length][1];
 
             for (int j = 0; j<Base.length; j++){                  
                    C_B1[j][0] = ObjF.getArray()[Base[j]][0];                                                  
             }
 
             Matrix C_B = new Matrix(C_B1);
//             System.out.println("C_B:");
//             C_B.print(2, 2);
             return C_B;
       }

    private boolean generateConstraintsLeftMatrix(String[] equations, boolean isMax){
        double[][] matr = new double[equations.length-1][];
        int slackPos = 1;
        ArrayList<Integer> ExcessVarsPos = calculatePosExcess(equations);
        ArrayList<Integer> EqualityConstPos = calculatePosEqualities(equations);
        ArrayList<Variable> toEnter = new ArrayList();
        ArrayList<Double> valuesTE = new ArrayList();
        boolean isBigM = false;
        for (int i = 1; i < equations.length; i++) {
                String[] caracteres = equations[i].split(" ");
                double[] equation = new double[nVarDecision + equations.length-1 + ExcessVarsPos.size()];
                int j = 2;
                for (j = 0; j < nVarDecision; j++) {
                    equation[j] = Double.parseDouble(caracteres[j*2+2]);
                }
                try {
                    if(i == slackPos)
                        equation[i + nVarDecision-1] = 1;
                    
                    if(ExcessVarsPos.contains(i)){
                        equation[equation.length - ExcessVarsPos.size() + ExcessVarsPos.indexOf(i)] = -1;
                        FObj.getArray()[i + nVarDecision-1][0] = BIG_M;
                        isBigM = true;
                        if(isMax)
                            FObj.getArray()[i + nVarDecision-1][0] *= -1;
                        toEnter.add(new Variable("A", Variable.CONTINUOUS));
                        valuesTE.add(FObj.getArray()[i + nVarDecision-1][0]);
                    }
                    if(EqualityConstPos.contains(i)){
                        FObj.getArray()[i + nVarDecision-1][0] = BIG_M;
                        isBigM = true;
                        if(isMax)
                            FObj.getArray()[i + nVarDecision-1][0] *= -1;
                        model.addVariable("A", Variable.CONTINUOUS, FObj.getArray()[i + nVarDecision-1][0]);
                    } else {
                        model.addVariable("S", Variable.CONTINUOUS, 0);
                         }
                        } catch (Exception ex) {
                            Logger.getLogger(Simplex.class.getName()).log(Level.SEVERE, null, ex);
                        }
                slackPos++;
                matr[i-1] = equation;
            }
        for (int i = 0; i < toEnter.size(); i++) {
            try {
                model.addVariable(toEnter.get(i).getName(), toEnter.get(i).getType(), valuesTE.get(i));
            } catch (Exception ex) {
                Logger.getLogger(Simplex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//            System.out.println("cantidad de variables"+toEnter.size() + " <- holgura negativos" + model.getVariableCount());
            ConsLeft = new Matrix(matr);
            if(isBigM){
                enlargeFO(ExcessVarsPos.size());
                normalizeBigM(ExcessVarsPos.size() + EqualityConstPos.size(), equations);
            }
            return isBigM;
//            System.out.println("Constantes izquierda");
//            ConsLeft.print(2,2);
    }

    private void generateEquaAndFOMatries(String[] equations) {
        String[] objective = equations[0].split(" ");
        int nVarDecision = objective.length/2-2;
        double[][] toFO = new double[equations.length-1 + nVarDecision][1];
//        double[] intoC = new double[nVarDecision + equations.length-1];
        for (int i = 0; i < nVarDecision; i++) {
            toFO[i][0] = Double.parseDouble(objective[i*2+2]) * (-1);
        }
//        toC[0] = intoC;
        FObj = new Matrix(toFO);
        FObj.print(2, 2);
//        double[] intob = new double[equations.length-1];
        double[][] tob = new double[equations.length-1][1];
        for (int i = 1; i < equations.length; i++) {
            String[] caracteres = equations[i].split(" ");
            tob[i-1][0] = Double.parseDouble(caracteres[caracteres.length-1]);
        }
//        tob[0] = intob;
        equalities = new Matrix(tob);
        System.out.println("igualdades:");
        equalities.print(2, 2);
    }

        private boolean quotientTest(Matrix last) {
        last.print(2, 2);
        Matrix leftC = last.getMatrix(1, last.getRowDimension()-1, 0, last.getColumnDimension()-2);
        double[][] matr = leftC.getArray();
        boolean procd = false;
        Matrix equality = last.getMatrix(1, last.getRowDimension()-1, last.getColumnDimension()-1, last.getColumnDimension()-1);
        Matrix eObjective = last.getMatrix(0, 0, 0, last.getColumnDimension()-2).transpose();
        double[][] eObjec = eObjective.getArray();
        double [][] igualdad = equality.getArray();
        double masGrande = 0;
        int posMasG = -1;
        for (int i = 0; i < eObjec.length; i++) {
            if((eObjec[i][0] < 0 && model.getType().equals(Model.MAXIMIZE)) || (eObjec[i][0] > 0 && !model.getType().equals(Model.MAXIMIZE))){
                masGrande = eObjec[i][0];
                posMasG = i;
            }
        }
        if(masGrande != 0)
            procd = true;
        double[] theta = new double[Base.length];
        double rowLow = Double.MAX_VALUE;
        int posLow = -1;
        if(procd){
        for (int i = 0; i < Base.length; i++) {
            System.out.println(posMasG);
            theta[i] = igualdad[i][0] / matr[i][posMasG];
            System.out.println(theta[i]);
            if(rowLow>theta[i] && theta[i] > 0){
                rowLow = theta[i];
                posLow = i;
            }
        }
        Base[posLow] = posMasG;
        for (int i = 0; i < Base.length; i++) {
                System.out.println(Base[i]);
            }
        }
        return procd;
    }
    public static void main(String[] args) {
//        Simplex s = new Simplex("MAXIMIZE", new String[] {"1 Z -3 X1 -5 X2 = 0",
//                                           "0 Z 1 X1 0 X2 <= 4",
//                                           "0 Z 0 X1 2 X2 <= 12",
//                                           "0 Z 3 X1 2 X2 <= 18"});
        Simplex s = new Simplex("MINIMIZE", new String[] {"1 Z -2 X1 -3 X2 = 0",
                                           "0 Z 0.5 X1 0.25 X2 <= 4",
                                           "0 Z 1 X1 3 X2 >= 20",
                                           "0 Z 1 X1 1 X2 = 10"});
    }

    private ArrayList <Integer> calculatePosExcess(String[] equations) {
        ArrayList <Integer>resultado = new ArrayList();
        for (int i = 0; i < equations.length; i++) {
            String [] actual = equations[i].split(" ");
            if(actual[actual.length-2].equals(">="))
                resultado.add(i);
        }
        return resultado;
    }

    private ArrayList<Integer> calculatePosEqualities(String[] equations) {
        ArrayList <Integer>resultado = new ArrayList();
        for (int i = 1; i < equations.length; i++) {
            String [] actual = equations[i].split(" ");
            if(actual[actual.length-2].equals("="))
                resultado.add(i);
        }
        return resultado;
        }

    private void normalizeBigM(int emes, String []equations) {
        System.out.println("emes " +emes);
        double[][] aNormalizar = ConsLeft.getArray();
            calculateInitialBase();
            internalteration(!model.getType().equals(Model.MAXIMIZE));
             Final.print(2, 2);
    }

    private void enlargeFO(int excess) {
        double[][] newFO = new double[FObj.getRowDimension()+excess][1];
        double [][] oldFO = FObj.getArray();
        for (int i = 0; i < FObj.getRowDimension(); i++) {
            newFO[i][0] = oldFO[i][0];
        }
//        for (int i = 0; i < excess; i++) {
//            try {
//                model.addVariable("S", Variable.CONTINUOUS, 0);
//            } catch (Exception ex) {
//                Logger.getLogger(Simplex.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        FObj = new Matrix(newFO);
        FObj.print(2, 2);
        }

    private void internalteration(boolean isMax) {
        MId = CreaB(ConsLeft, Base);
        SlackOF = takeSlackOF(FObj, Base);
        Matrix B_inv = MId.inverse(); 
        Matrix X_B = B_inv.times(equalities);            
        Matrix TAB = B_inv.times(ConsLeft);
        Matrix Fila_z = ((SlackOF.transpose()).times(TAB)).minus(FObj.transpose());
        Matrix z_v = (SlackOF.transpose()).times(X_B);
        Final = CrearTabla(TAB, X_B, Fila_z, z_v);
    }

    private boolean isFactibleSolution() {
        
        return false;
    }

    @Override
    public Solution solve(Model model) {
        
        return null;
    }
}
