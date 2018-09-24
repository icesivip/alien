using MetodoGrafico.modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MetodoGrafico
{
    public partial class MetodoGrafico : Form
    {
        public MetodoGrafico()
        {
            InitializeComponent();
            inicializarModelo();
 
        }


        private void inicializarModelo()
        {
            model = new modelo.Modelo();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void tableLayoutPanel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnAgregar_Click(object sender, EventArgs e)
        {
            Console.WriteLine("Pasando de " + tableLayoutPanel2.RowCount + " filas a " + (tableLayoutPanel2.RowCount + 1));
            tableLayoutPanel2.RowStyles.Add(new RowStyle(SizeType.Absolute,20F));
            tableLayoutPanel2.Controls.Add(numericFormateado(), 0, tableLayoutPanel2.RowCount);
            tableLayoutPanel2.Controls.Add(numericFormateado(), 1, tableLayoutPanel2.RowCount);
            tableLayoutPanel2.Controls.Add(comboBoxFormateado(), 2, tableLayoutPanel2.RowCount);
            tableLayoutPanel2.Controls.Add(numericFormateado(), 3, tableLayoutPanel2.RowCount);
            tableLayoutPanel2.RowCount++;
        }

        private ComboBox comboBoxFormateado()
        {
            ComboBox c = new ComboBox();

            c.DropDownStyle = ComboBoxStyle.DropDownList;

            c.Dock = System.Windows.Forms.DockStyle.Fill;
            c.FormattingEnabled = true;
            c.Items.AddRange(new object[] {
            "<=",
            ">=",
            "="});
            c.SelectedIndex = 0;
          



            return c;
        }


        private NumericUpDown numericFormateado()
        {
            NumericUpDown num = new NumericUpDown();
            num.DecimalPlaces = 2;
            num.Dock = System.Windows.Forms.DockStyle.Fill;
            num.Maximum = new decimal(new int[] {
            10000,
            0,
            0,
            0});
            num.Minimum = new decimal(new int[] {
            10000,
            0,
            0,
            -2147483648});
            return num;
        }

        private void btnDibujar_Click(object sender, EventArgs e)
        {
            model.clearRest();
            model.xObj = (double)numericUpDown1.Value;
            model.yObj = (double)numericUpDown2.Value;
            model.tipoObj = comboBox1.SelectedItem.ToString();
            for (int i = 1; i < tableLayoutPanel2.RowCount; i++)
            {

                NumericUpDown X = (NumericUpDown)tableLayoutPanel2.GetControlFromPosition(0, i);
                NumericUpDown Y = (NumericUpDown)tableLayoutPanel2.GetControlFromPosition(1, i);
                ComboBox TIPO=(ComboBox)tableLayoutPanel2.GetControlFromPosition(2, i);
                NumericUpDown C = (NumericUpDown)tableLayoutPanel2.GetControlFromPosition(3, i);
                double x = Decimal.ToDouble(X.Value);
                double y = Decimal.ToDouble(Y.Value);
                string tipo = TIPO.SelectedItem.ToString();
                double coef = Decimal.ToDouble(C.Value);


                model.addRest(x, y, tipo, coef);
            }

            //for (int i = 0; i < model.restricciones.Count; i++)
            //{
            //    for (int j = i+1; j < model.restricciones.Count; j++)
            //    {
            //        Punto p = model.corteEntreRestricciones(model.restricciones[i], model.restricciones[j]);
            //        Console.WriteLine("X:{0}   Y:{1}", p.x, p.y);
            //    }
            //}
            
            panel1.Refresh();
            
            model.valorOptimo();
        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

    

            Console.WriteLine("RePaint panel...");
            var g = e.Graphics;

            var p = sender as Panel;
            Brush b = new SolidBrush(Color.Black);
            Pen pen = new Pen(b);
            //g.DrawRectangle(pen, 10, 10, 60, 60);
            g.DrawLine(pen, 10, 10, 10, p.Height - 10);
            Point origenEjes = new Point(10, p.Height - 10);
            Point limiteEjeX = new Point(10, 10);
            Point limiteGrafica = new Point(p.Width - 10, 10);
            int tamEjeX = Math.Abs(p.Width - 20);
            g.DrawLine(pen,10, p.Height-10, p.Width-10, p.Height - 10);
            Point limiteEjeY = new Point(p.Width - 10, p.Height - 10);
            int tamEjeY = Math.Abs(p.Height - 20);
            Font f = new Font("H", 7, FontStyle.Regular);
            g.DrawString("Y",f, b, 7-1, -2);
            g.DrawString("X",f, b, p.Width-10-1, p.Height-13-3);
            float divison = tamEjeY / 10;
            float divison2 = tamEjeX / 10;
            Console.WriteLine(tamEjeY);
          
            //dibujo de ejes
            for (int i=1; i<=10; i++)
            {
                g.DrawLine(pen, origenEjes.X, origenEjes.Y - (divison * i), origenEjes.X + 5, origenEjes.Y - (divison * i));
            }

            for (int i = 1; i <= 10; i++)
            {
                g.DrawLine(pen, origenEjes.X + (divison2 * i), origenEjes.Y, origenEjes.X + (divison2 * i), origenEjes.Y - 5);
            }


            //logica dibujo vertices
            List<Punto> puntos = model.darVerticesTotales();

            if (puntos.Count > 0)
            {

                double maxX = puntos.Max(i => i.x);
                double maxY = puntos.Max(i => i.y);

                if (maxX == 0)
                {
                    maxX = 10;
                }

                if (maxY == 0)
                {
                    maxY = 10;
                }

                for (int i = 1; i <= 10; i++)
                {

                    g.DrawString(String.Format("{0:0.##}", maxY * (i / 10.0)), f, b, origenEjes.X + 5, origenEjes.Y - (divison * i) - 6);
                }


                for (int i = 1; i <= 10; i++)
                {
                    g.DrawString(String.Format("{0:0.##}", maxX * (i / 10.0)), f, b, origenEjes.X + (divison2 * i) - 5, origenEjes.Y - 10 - 7);

                }


                List<Restriccion> rest = model.restricciones;
                pen.DashPattern=new float[]{ 3, 2, 3, 2 };
                foreach (Restriccion r in rest)
                {
                    List<Punto> cortes = r.corteEjes();
                    cortes = cortes.Where(i => i.x >= 0 && i.y >= 0).ToList();
                    if (cortes.Count == 2)
                    {
                        Point drawX1 = transformar(cortes[0], maxX, maxY, tamEjeX, tamEjeY, origenEjes);
                        Point drawX2 = transformar(cortes[1], maxX, maxY, tamEjeX, tamEjeY, origenEjes);
                        Console.WriteLine("X1:{0}  Y1:{1}  X2:{2}  Y2:{3}", drawX1.X, drawX1.Y, drawX2.X, drawX2.Y);
                        Point[] points;
                        b = new SolidBrush(Color.FromArgb(255/rest.Count, 0, 255, 0));
                        if (r.tipo.Equals(r.MAYOR_IGUAL))
                        {
                            points =new Point[] { drawX2, drawX1, limiteEjeX, limiteGrafica,limiteEjeY};
                            g.FillPolygon(b, points);
                            g.DrawLine(pen, drawX1, drawX2);
                        }
                        else if (r.tipo.Equals(r.MENOR_IGUAL))
                        {
                            points = new Point[] { drawX1, origenEjes, drawX2 };
                            g.FillPolygon(b, points);
                            g.DrawLine(pen, drawX1, drawX2);
                        }
                        else
                        {
                            g.DrawLine(pen, drawX1, drawX2);
                        }
                        b = new SolidBrush(Color.Black);
                        //g.DrawLine(pen, drawX1, drawX2);
                    }else if(cortes.Count==1)
                    {
                        Point drawX1 = transformar(cortes[0], maxX, maxY, tamEjeX, tamEjeY, origenEjes);
                        double y = (r.cX * maxX) / (-r.cY);
                        Punto aux = new Punto(maxX, y);
                        Point drawX2 = transformar(aux, maxX, maxY, tamEjeX, tamEjeY, origenEjes);
                        Console.WriteLine("X1:{0}  Y1:{1}  X2:{2}  Y2:{3}", drawX1.X, drawX1.Y, drawX2.X, drawX2.Y);
                        g.DrawLine(pen, drawX1, drawX2);
                    }
                }

                


                for (int i = 1; i <= 10; i++)
                {

                    g.DrawString(String.Format("{0:0.##}", maxY * (i / 10.0)), f, b, origenEjes.X + 5, origenEjes.Y - (divison * i) - 6);
                }


                for (int i = 1; i <= 10; i++)
                {
                    g.DrawString(String.Format("{0:0.##}", maxX * (i / 10.0)), f, b, origenEjes.X + (divison2 * i) - 5, origenEjes.Y - 10 - 7);

                }

            }


            //dibujo de ejes
            for (int i = 1; i <= 10; i++)
            {
                g.DrawLine(pen, origenEjes.X, origenEjes.Y - (divison * i), origenEjes.X + 5, origenEjes.Y - (divison * i));
            }

            for (int i = 1; i <= 10; i++)
            {
                g.DrawLine(pen, origenEjes.X + (divison2 * i), origenEjes.Y, origenEjes.X + (divison2 * i), origenEjes.Y - 5);
            }

            //

            //    public Point transformar(Punto p, )
            //{

            //}

















            //Point[] points = new Point[4];
            //points[0] = new Point(50, 50);
            //points[1] = new Point(110, 50);
            //points[2] = new Point(110, 110);
            //points[3] = new Point(50, 110);
            //Brush b = new SolidBrush(Color.Blue);
            //g.FillRectangle(b, 50, 50, 60, 60);
            //g.FillPolygon(b, points);

        }


        private Point transformar(Punto p , double maxX, double maxY, int tamEjeX, int tamEjeY, Point origenEjes)
        {
           
            double px = p.x * (tamEjeX/maxX);
            
            double py = p.y *(tamEjeY/maxY);
            return new Point((int)(origenEjes.X+px), (int)(origenEjes.Y - py));
        }


    }
}
