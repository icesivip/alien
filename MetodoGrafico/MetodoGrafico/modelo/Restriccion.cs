using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MetodoGrafico.modelo
{
    class Restriccion
    {

        public readonly String MAYOR_IGUAL = ">=";
        public readonly String MENOR_IGUAL = "<=";
        public readonly String IGUAL = "=";


        public double cX;
        public double cY;
        public String tipo;
        public double cDerecha;


        public Restriccion(double x, double y, String t, double coef)
        {
            cX = x;
            cY = y;
            tipo = t;
            cDerecha = coef;
        }

        public Boolean cumpleRestriccion(Punto p)
        {
            if (tipo.Equals(this.IGUAL))
            {
                if (cX * p.x + cY * p.y >= cDerecha-0.0001 && cX * p.x + cY * p.y <= cDerecha + 0.0001)
                {
                    Console.WriteLine("El punto {0},{1} cumple con la restriccion.", p.x, p.y);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (tipo.Equals(this.MAYOR_IGUAL))
            {
                if(cX*p.x+ cY * p.y >= cDerecha)
                {
                    return true;
                }else
                {
                    return false;
                }
            }else
            {
                if (cX * p.x + cY * p.y <= cDerecha)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

        }

        public List<Punto> corteEjes()
        {


            List<Punto> cortes = new List<Punto>();
            if (cX == 0 || cY == 0)
            {
                if (cX == 0)
                {
                    cortes.Add(new Punto(0, cY));
                }else
                {
                    cortes.Add(new Punto(cX, 0));
                }
            }else
            {
                cortes.Add(new Punto(0, cDerecha / cY));
                cortes.Add(new Punto(cDerecha / cX, 0));
            }
            return cortes;

        }


        public Boolean Equals(Restriccion r)
        {
            return r.cX == cX && r.cY == cY && r.cDerecha == cDerecha;
        }

    }
}
