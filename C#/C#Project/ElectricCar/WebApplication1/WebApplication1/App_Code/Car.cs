using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Car 的摘要说明
/// </summary>
namespace bookstore {
    public class Car {
        int carId;
        public int CarId { get; set; }

        int categoryId;
        public int CategoryId { get; set; }

        string title;
        public string Title { get; set; }

        string description;
        public string Description { get; set; }

        string carImage;
        public string CarImage { get; set; }

        decimal salePrice;
        public decimal SalePrice { get; set; }

        int quantity;
        public int Quantity { get; set; }

        decimal sumOfMoney;
        public decimal SumOfMoney { get; set; }
    }
}