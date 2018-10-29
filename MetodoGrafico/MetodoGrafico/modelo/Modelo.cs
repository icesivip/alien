using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MetodoGrafico.modelo
{
    class Modelo
    {

        public static readonly string MAX = "MAX";
        public static readonly string MIN = "MIN";
        public double xObj;
        public double yObj;
        public String tipoObj;
        public List<Restriccion> restricciones;
       

        public Modelo()
        {
            xObj = 0;
            yObj = 0;
            tipoObj = MAX;
            clearRest();

        }

        public List<Punto> darVerticesTotales()
        {
            List<Punto> puntos = new List<Punto>();

            puntos.Add(new Punto(0, 0));
            foreach (Restriccion r in restricciones)
            {
                puntos.AddRange(r.corteEjes());
            }

            for (int i = 0; i < restricciones.Count; i++)
            {
                for (int j = i + 1; j < restricciones.Count; j++)
                {
                    puntos.Add(corteEntreRestricciones(restricciones[i], restricciones[j]));
                }
            }
            return puntos.Where(p => p.x >= 0 && p.y >= 0).ToList();
        }

        public void valorOptimo()
        {
            List<Punto> puntos = darVerticesTotales();

            
            puntos = puntos.Where(p => cumpleTodasRestricciones(p)).ToList();

            if (tipoObj.Equals(MAX))
            {
                Punto max = null;
                double maxF = 0;
                foreach (Punto p in puntos)
                {
                    if(p.x*xObj + p.y*yObj >= maxF)
                    {
                        max = p;
                        maxF = p.x * xObj + p.y * yObj;
                    }
                }
                if (max != null)
                {
                    String m = String.Format("PUNTO MAXIMO: X:{0}  Y:{1}", max.x, max.y);
                    MessageBox.Show(m);
                //Console.WriteLine("PUNTO MAXIMO: X:{0}  Y:{1}",max.x,max.y);
                }
            }else
            {
                Punto min = null;
                double minF = 0;
                foreach (Punto p in puntos)
                {
                    if (p.x * xObj + p.y * yObj <= minF)
                    {
                        min = p;
                        minF = p.x * xObj + p.y * yObj;
                    }
                }
                if (min != null)
                {
                    String m = String.Format("PUNTO MINIMO: X:{0}  Y:{1}", min.x, min.y);
                    MessageBox.Show(m);
                    Console.WriteLine("PUNTO MINIMO: X:{0}  Y:{1}", min.x, min.y);
                }
            }
        }

        public bool cumpleTodasRestricciones(Punto p)
        {
            return restricciones.All(i => i.cumpleRestriccion(p));
        }

        public void clearRest()
        {
            restricciones = new List<Restriccion>();
        }

        public void addRest(double x, double y, string tipo, double coef)
        {
            restricciones.Add(new Restriccion(x, y, tipo, coef));
        }

        public bool restriccionesIguales(Restriccion r1, Restriccion r2)
        {
            return r1.Equals(r2);
        }

        
        public override String ToString()
        {
            return xObj + " " + yObj + " " + tipoObj + " " +restricciones.Count;
        }



        public Punto corteEntreRestricciones(Restriccion r1, Restriccion r2)
        {
            if(r1.cY==0 && r2.cY == 0 || (r1.cX == 0 && r2.cX == 0))
            {
                return null;
            }else if(r1.cX==0 && r2.cY == 0)
            {
                return new Punto(r2.cX, r1.cY);
            }
            else if (r1.cY == 0 && r2.cX == 0)
            {
                return new Punto(r1.cX, r2.cY);
            }else
            {
                double y = ((r1.cDerecha * r2.cX) - (r1.cX * r2.cDerecha) )/ ((r2.cX * r1.cY) - (r1.cX * r2.cY));
                double x = (r1.cDerecha - (r1.cY * y)) / r1.cX;
                return new Punto(x, y);
            }
        }
    }
}
