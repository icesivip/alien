
import Jama.Matrix;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis
 */
public class SimplexTest {
           public static void main(String[] args) {
             // TODO Auto-generated method stub
 
 
             Matrix A ;
             Matrix B ;
             Matrix C;
             Matrix C_B;
             Matrix b;
             int [] Base;
 
             A = new Matrix(new double[][]{{1, 0, 1, 0, 0},
                                           {0, 2, 0, 1, 0},
                                           {3, 2, 0, 0, 1}});
 
             C = new Matrix(new double[][]{{3},{5},{0},{0},{0}});
             b = new Matrix(new double [][] {{4}, {12},{18}});
 
             
 
             Base = new int []{2,3,4};
             
             B = CreaB(A, Base);
             C_B = CreaC_B(C, Base);
             
 
             
 
             Matrix B_inv = B.inverse(); 
             Matrix X_B = B_inv.times(b);            
             Matrix TAB = B_inv.times(A);
             Matrix Fila_z = ((C_B.transpose()).times(TAB)).minus(C.transpose());
             Matrix z_v = (C_B.transpose()).times(X_B);
             Matrix Final = CrearTabla(TAB, X_B, Fila_z, z_v);
             Final.print(2,2);
 
 
             Base = new int []{2,1,4};
             
 
             
             B = CreaB(A, Base);
             C_B = CreaC_B(C, Base);
             
 
       
             B_inv = B.inverse(); 
             X_B = B_inv.times(b);             
             TAB = B_inv.times(A);             
             Fila_z = ((C_B.transpose()).times(TAB)).minus(C.transpose());
             z_v = (C_B.transpose()).times(X_B);
             Final = CrearTabla(TAB, X_B, Fila_z, z_v);
             Final.print(2,2);
 
 
             Base = new int []{2,1,0};
             
             B = CreaB(A, Base);
             C_B = CreaC_B(C, Base);
             
             
             B_inv = B.inverse(); 
             X_B = B_inv.times(b);             
             TAB = B_inv.times(A);             
             Fila_z = ((C_B.transpose()).times(TAB)).minus(C.transpose());
             z_v = (C_B.transpose()).times(X_B);
             Final = CrearTabla(TAB, X_B, Fila_z, z_v);
             Final.print(2,2);
 
 
       }
 
 
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
 
       public static Matrix CreaB(Matrix A, int [] Base){
 
             double [][] B1 = new double [Base.length][Base.length];
 
             for (int i =0; i<Base.length; i++){
                    for (int j =0; j<Base.length; j++){
                           B1[i][j] = A.getArray()[i][Base[j]];                                                   
                    }
             }
 
             Matrix B = new Matrix(B1);
             return B;
       }
       public static Matrix CreaC_B(Matrix C, int [] Base){
             double [][] C_B1 = new double [Base.length][1];
 
             for (int j = 0; j<Base.length; j++){                  
                    C_B1[j][0] = C.getArray()[Base[j]][0];                                                  
             }
 
             Matrix C_B = new Matrix(C_B1);
             return C_B;
       }
}
