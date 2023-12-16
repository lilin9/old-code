using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Web;

namespace bookstore
{
    public class OrderInfo
    {
        string orderId;
        public string OrderId
        {
            get { return orderId; }
            set { orderId = value; }
        }
        int userId;
        public int UserId
        {
            get { return userId; }
            set { userId = value; }
        }
        DateTime orderDate;
        public DateTime OrderDate
        {
            get { return orderDate; }
            set { orderDate = value; }
        }
        string telephone;
        public string Telephone
        {
            get { return telephone; }
            set { telephone = value; }
        }
        string address;
        public string Address
        {
            get { return address; }
            set { address = value; }
        }
        string realName;
        public string RealName
        {
            get { return realName; }
            set { realName = value; }
        }
        decimal totalPrice;
        public decimal TotalPrice
        {
            get { return totalPrice; }
            set { totalPrice = value; }
        }
        IList<Book> orderDetails;
        public IList<Book> OrderDetails
        {
            get { return orderDetails; }
            set { orderDetails = value; }
        }
        string status;
        public string Status
        {
            get { return status; }
            set { status = value; }
        }

        public OrderInfo()
        {
            this.OrderId = DateTime.Now.Ticks.ToString();
            this.OrderDate = DateTime.Now;
            this.OrderDetails = new List<Book>();        
        }
        
    }
}