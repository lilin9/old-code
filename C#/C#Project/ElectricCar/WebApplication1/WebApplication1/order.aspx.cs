using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace bookstore {
    public partial class order : System.Web.UI.Page {
        protected void Page_Load(object sender, EventArgs e) {
            if (!IsPostBack) {
                if (this.Session["UserId"] != null) {
                    //;
                    string sql = "SELECT `Order`.`OrderId`,`OrderDate`,`Telephone`,`Address`,`RealName`,`TotalPrice`,`Status`,`Title`,`OrderDetail`.`SalePrice`,`OrderDetail`.`Quantity` FROM `Order`,`OrderDetail`,`car` WHERE `Order`.`OrderId`=`OrderDetail`.`OrderId` AND `OrderDetail`.`CarId`=`car`.`CarId` AND `UserId`=@UserId" + ";";
                    MySqlParameter[] parameter = new MySqlParameter[]
                    {
                            new MySqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                    };
                    DataTable dt = DB.GetDataSet(sql, parameter);
                    IList<OrderInfo> orderInfoes = new List<OrderInfo>();
                    OrderInfo orderInfo = new OrderInfo();
                    string tempOrderId = "11111";
                    for (int i = 0; i < dt.Rows.Count; i++) {
                        if (tempOrderId != dt.Rows[i]["OrderId"].ToString()) {
                            orderInfo = new OrderInfo();
                            orderInfoes.Add(orderInfo);
                            orderInfo.OrderId = dt.Rows[i]["OrderId"].ToString();
                            orderInfo.OrderDate = Convert.ToDateTime(dt.Rows[i]["OrderDate"]);
                            orderInfo.Telephone = dt.Rows[i]["Telephone"].ToString();
                            orderInfo.Address = dt.Rows[i]["Address"].ToString();
                            orderInfo.RealName = dt.Rows[i]["RealName"].ToString();
                            orderInfo.TotalPrice = Convert.ToDecimal(dt.Rows[i]["TotalPrice"]);
                            orderInfo.Status = dt.Rows[i]["Status"].ToString();
                            tempOrderId = orderInfo.OrderId;
                        }
                        Car car = new Car();
                        car.Title = dt.Rows[i]["Title"].ToString();
                        car.SalePrice = Convert.ToDecimal(dt.Rows[i]["SalePrice"]);
                        car.Quantity = Convert.ToInt32(dt.Rows[i]["Quantity"]);
                        car.SumOfMoney = car.SalePrice * car.Quantity;
                        orderInfo.OrderDetails.Add(car);
                    }
                    this.rptOrder.DataSource = orderInfoes;
                    this.rptOrder.DataBind();

                } else {
                    Response.Write("<script>alert('请先登录账号')</script >");
                }

            }
        }
    }
}
