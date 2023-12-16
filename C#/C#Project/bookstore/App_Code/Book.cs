using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace bookstore
{
    public class Book
    {
        int bookId;
        public int BookId
        {
            get { return bookId; }
            set { bookId = value; }
        }

        int categoryId;
        public int CategoryId
        {
            get { return categoryId; }
            set { categoryId = value; }
        }

        string bookName;
        public string BookName
        {
            get { return bookName; }
            set { bookName = value; }
        }

        string author;
        public string Author
        {
            get { return author; }
            set { author = value; }
        }

        string publisher;
        public string Publisher
        {
            get { return publisher; }
            set { publisher = value; }
        }

        DateTime publishDate;
        public DateTime PublishDate
        {
            get { return publishDate; }
            set { publishDate = value; }
        }

        string description;
        public string Description
        {
            get { return description; }
            set { description = value; }
        }

        string bookImage;
        public string BookImage
        {
            get { return bookImage; }
            set { bookImage = value; }
        }

        string isbn;
        public string ISBN
        {
            get { return isbn; }
            set { isbn = value; }
        }

        decimal salePrice;
        public decimal SalePrice
        {
            get { return salePrice; }
            set { salePrice = value; }
        }

        int quantity;
        public int Quantity
        {
            get { return quantity; }
            set { quantity = value; }
        }

        decimal sumOfMoney;

        public decimal SumOfMoney
        {
            get
            {
                sumOfMoney = this.SalePrice * this.Quantity;
                return sumOfMoney;
            }
            set { sumOfMoney = value; }
        }


    }
}