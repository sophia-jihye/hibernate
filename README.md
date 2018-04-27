# hibernate
study note and sample code for hibernate

#### Features of Hibernate 
	1. Easy to use multiple database
		- Just add another config file and load
	2. Does not require to declare specific query(Ex. `select book_nm from book;`) in .xml file
		- Just declare the table name and columns
		- More intuitive to view the one object as a data of one row
		- ex.	Insert = create object, send the object as a parameter and call commit API
				Select = send pk as a parameter and call get API
				Update = modify the property of object, send the object as a parameter and call update API
				Delete = send the object as a parameter and call delete API
		- If you want to handle with somewhat complicated query (Ex. paging query), mybatis would be more better to use.

#### Steps to configure multiple database
	1. Add configuration file under 'config' folder and set database information
		- Refer to ./hibernate/config/hibernate.cfg.xml
	2. In ./hibernate/src/sophia/hibernate/manager/SessionManager class, add or edit method that handles configuration file
		- Add method named getSessionFactory${seq}
		- Edit method named closeSessionFactory()
	3. Call different sessionFactory in TestMain class
		- You can open multiple SessionFactory
		- Send the sessionFactory as the first parameter while calling the CRUD methods
	
