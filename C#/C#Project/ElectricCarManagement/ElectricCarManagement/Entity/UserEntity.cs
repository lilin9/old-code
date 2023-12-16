using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ElectricCarManagement.Entity {
    internal class UserEntity {
        public int UserId { get; set; }

        private string userName;
        public string Username { get; set; }

        private string password;
        public string Password { get; set; }

        private string sex;
        public string Sex { get; set; }

        private string realName;
        public string RealName { get; set; }

        private string telephone;
        public string Telephone { get; set; }

        private string address;
        public string Address { get; set; }

        private string rank;
        public string Rank { get; set; }
    }
}
