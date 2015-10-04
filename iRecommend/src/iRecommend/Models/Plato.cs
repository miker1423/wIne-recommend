using System.Collections.Generic;

namespace iRecommend.Models
{
    public class Plato
    {
        public string Nombre { get; set; }
        public List<Tipo> tipos{ get; set; }   
    }
}