package payment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import common.JsonReturn;
import payment.model.service.PaymentService;
import payment.model.vo.Payment;

/**
 * Servlet implementation class PaymentInsertSerlvet
 */
@WebServlet("/payins")
public class PaymentInsertSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentInsertSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int pa_num = Integer.parseInt(request.getParameter("pa_num"));
		int me_num = Integer.parseInt(request.getParameter("me_num"));
		int pm_amount = Integer.parseInt(request.getParameter("pm_amount"));
		
		String pm_host = request.getParameter("pm_host");
		String pm_deposit = request.getParameter("pm_deposit");
		String pm_method = request.getParameter("pm_method");
		
		System.out.println("pa_num : " + pa_num + " me_num : " + me_num 
				+ " pm_amount : " + pm_amount + " pm_host : " + pm_host 
				+ " pm_deposit : " + pm_deposit + " pm_method : " + pm_method);
		
		Payment payment = new Payment(me_num, pa_num, pm_host, pm_amount, pm_deposit, pm_method);
		int result = new PaymentService().insertPayment(payment);
		
		if(result > 0) {
			
			JSONObject sendJson = new JsonReturn().returnPayment(payment);
			System.out.println("결제 저장 성공 sendJson : " + sendJson);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(sendJson);
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
