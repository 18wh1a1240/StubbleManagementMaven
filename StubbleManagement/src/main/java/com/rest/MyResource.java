package com.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dto.Farmer;
import com.rest.dto.Manufacturer;
import com.rest.dto.Product;
import com.rest.dto.PurchaseOrder;
import com.ts.dao.FarmerDAO;
import com.ts.dao.ManufacturerDAO;
import com.ts.dao.ProductDAO;
import com.ts.dao.PurchaseOrderDAO;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    // Use This myresource for your project building
    @Path("regFarmer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void registerFarmer(Farmer farmer){
    	System.out.println("Data received in register Farmer " + farmer);
    	FarmerDAO farmerDao = new FarmerDAO();
    	farmerDao.register(farmer);
    }
    @Path("regManufacturer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void registerManufacturer(Manufacturer manufacturer){
    	System.out.println("Data received in register Manufacturer " + manufacturer);
    	ManufacturerDAO manufacturerDao = new ManufacturerDAO();
    	manufacturerDao.register(manufacturer);
    }
    
    
    @Path("regProduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void registerProduct(Product product){
    	System.out.println("Data received in register Product " + product); 	
    	product.setStatus("available");
    	ProductDAO productDao = new ProductDAO();
    	productDao.register(product);
    }
    
  
    @Path("getAllFarmers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Farmer> getAllFarmers() {
    	System.out.println("Recieved in getAllFarmers "); 
		FarmerDAO farmerDao = new FarmerDAO();
		List<Farmer> farmer = farmerDao.getAllFarmers();
		System.out.println(farmer); 
		return farmer;

	}
    
    @Path("farmerLogin/{aadhar}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Farmer getFarmerByAadhar(@PathParam("aadhar") String aadhar){
    	System.out.println(aadhar);
		System.out.println("Recieved in getFarmerByAadhar : " + aadhar ); 
		FarmerDAO farmerDao = new FarmerDAO();
		Farmer farmer = farmerDao.getFarmerByUserPass(aadhar);	
		
		
			return farmer;
		
		
		}
    @Path("manufacturerLogin/{mailId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Manufacturer getManufacturerByMailId(@PathParam("mailId") String mailId ){
    	//System.out.println(aadhar);
		System.out.println("Recieved in getManufacturerByMailId : " + mailId ); 
		ManufacturerDAO manufacturerDao = new ManufacturerDAO();
		Manufacturer manufacturer = manufacturerDao.getManufacturerByMail(mailId);	
		//System.out.println(farmer); 
		if(manufacturer != null) {
			return manufacturer;
		} else {
			return null;
		}
		
		}
    
    
    @Path("getAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getallProducts() {
		System.out.println("Recieved in getAllProducts "); 
		ProductDAO productDao = new ProductDAO();
		List<Product> products = productDao.getAllProducts();
		System.out.println(products); 
		return products;
	}
    
    /*@Path("purchaseOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void addOrder(List<Product> products, Manufacturer manufacturer){
    	//int res = 0;
    	PurchaseOrderDAO purchaseOrderDao = new PurchaseOrderDAO();
    	//ProductDAO productDao = new ProductDAO();
    	long millis=System.currentTimeMillis ();
    	java.sql.Date date=new java.sql.Date (millis);
    	
    	//System.out.println (date);
    	//Product pro = new Product();
    	for(Product pro: products) {
    		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    	    //Date date = new Date();  
    		PurchaseOrder po = new PurchaseOrder();
    		po.setPrice(pro.getPrice());
    		po.setQuantity(pro.getQuantity());
    		po.setProduct(pro);
    		po.setDateOfPurchase(date);
    		po.setDateOfDelivery(date);
    		po.setManufacturer(manufacturer);
    		purchaseOrderDao.register(po);
    	}
    	//return res;
    }*/
    
    @Path("purchaseOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void addOrder(PurchaseOrder purchaseorder){
    	PurchaseOrderDAO purchaseorderDao = new PurchaseOrderDAO();
    	purchaseorderDao.register(purchaseorder);
    	addorder1(purchaseorder.getProduct());
    }
    
    @Path("purchaseOrder1")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String addorder1(Product product) {
    	ProductDAO productDao = new ProductDAO();
    	//Product product = productDao.getProduct(productId);
    	productDao.updateProduct(product);
    	return "success";
    }
    
    @Path("getAllOrders/{manufacturerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseOrder> getAllOrders(@PathParam("manufacturerId") int manufacturerId) {
		System.out.println("Recieved in getAllOrders "); 
		PurchaseOrderDAO purchaseOrderDao = new PurchaseOrderDAO();
		List<PurchaseOrder> purchaseOrder = purchaseOrderDao.getPurchaseorders(manufacturerId);
		//System.out.println(purchaseOrder); 
		return purchaseOrder;
	}
    
    @Path("getProductByFarmerId/{farmerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getFarmerByAadhar(@PathParam("farmerId") int farmerId){
    	//System.out.println(aadhar);
		//System.out.println("Recieved in getFarmerByAadhar : " + aadhar + password); 
		ProductDAO productDao = new ProductDAO();
		List<Product> product = productDao.getProducts(farmerId);		
			return product;		
		}
    
    @Path("deleteproduct/{productId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("productId") int productId){
		ProductDAO productDao = new ProductDAO();
		int result = productDao.deleteProduct(Product.class,productId);
		System.out.println("delete product " + result);
				
		}
    
   /* @Path("Email")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public static List<String> hello(List<String> mailList,Manufacturer manufacturer) throws MessagingException {
		System.out.println("in myresource");
		//Address venue = event.getAddress();
		
		MimeMultipart content = new MimeMultipart("related");
		
		
		String subject="Stubble Management: ";
		String body="write body here";
		
		List<String> result = null; 
		System.out.println(mailList);
		System.out.println(mailList.size());
			String host = "smtp.gmail.com";
			String from = "stubblemanagement730@gmail.com";
			String pass = "Stubble@123";
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true"); // added this line
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			List<String> to = mailList; // added this line
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.size()];

			// To get the array of addresses

			for( int i=0; i < to.size(); i++ )
			{
				// changed from a while loop
				toAddress[i] = new InternetAddress(to.get(i));
			}

			for( int i=0; i < toAddress.length; i++)
			{
				// changed from a while loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			//message.setText(body);
			// This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         MimeBodyPart messageBodyPart = new MimeBodyPart();
	        
	         messageBodyPart.setContent(body, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         
	         message.setContent(multipart);
			//message.setContent(content);
			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
			System.out.println("success");
			result.add("success");
		System.out.println(result);
		return result;
	}*/
    
    @Path("message/{manufacturerName}/{mobile}/{productName}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public String get(@PathParam("manufacturerName") String manufacturerName , @PathParam("mobile") String mobile ,@PathParam("productName") String productName) {
    try {
		// Construct data
    	System.out.println("date");
		String apiKey = "apikey=" + "zrATIaOvl2E-XjjgrXGmjMY7PspKUiFpIqX9YRDLQA";
		//Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		//java.sql.Date sqldate = new java.sql.Date(date1.getTime());
		String msg = "Dear customer , your product " + productName + " has been purchased by "+ manufacturerName + " .Thanks for choosing our service.";
		System.out.println(msg);
		String message = "&message=" + msg;
		String sender = "&sender=" + "TXTLCL";
		mobile = "91"+mobile;
		String numbers = "&numbers=" + mobile;
		
		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
		String data = apiKey + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
		}
		rd.close();
		
		return stringBuffer.toString();
	} catch (Exception e) {
		System.out.println("Error SMS "+e);
		return "Error "+e;
	}
    }
}
