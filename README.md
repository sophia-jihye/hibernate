# hibernate
study note and sample code for hibernate

#### Features of Hibernate 
	1. Very intuitive to implement Object-Oriented Programming.
		- More intuitive to view the one object as a data of one row
	2. Easy to use multiple databases
		- Just add another config file and load
		- [Please Refer to 'Steps to use multiple databases' as below.]
	3. Does not require to declare specific query(Ex. `select book_nm from book;`) in .xml file
		- Just declare the table name and columns
		- ex.	Insert = create object, send the object as a parameter and call commit API
			Select = send pk as a parameter and call get API
			Update = modify the property of object, send the object as a parameter and call update API
			Delete = send the object as a parameter and call delete API
		- If you want to handle with somewhat complicated query (Ex. paging query), mybatis would be more better to use.
		[Please Refer to 'Things to consider when writing specific query ' as below.]
	4. There might be some overhead in executing query. 
		- Sometimes it executes extra query which was not declared by user in order to make sure that declared query is safe to execute.
		- Ex. When execuing 'delete' process, hibernate program first executes 'select by PK' query and then executes 'delete' query.   
		Sample log:	Hibernate: select book_.bookNm, book_.bookPrice as bookPrice3_ from book book_ where book_.bookNm=?
				Hibernate: delete from book where bookNm=?

#### Steps to use multiple databases
	1. Add configuration file under 'config' folder and set database information
		- Refer to ./hibernate/config/hibernate.cfg.xml
	2. In ./hibernate/src/sophia/hibernate/manager/SessionManager class, add or edit method that handles configuration file
		- Add method named getSessionFactory${seq}
		- Edit method named closeSessionFactory()
	3. Call different sessionFactory in TestMain class
		- You can open multiple SessionFactory
		- Send the sessionFactory as the first parameter while calling the CRUD methods
	
#### Things to consider when writing specific query 
	1. Every related table should be made as an object (Ex.sophia/model/Book.class)
	2. When using JOIN query, alias must be used.
	3. Table name under 'FROM' should be exactly the case-sensitive name of the class under model package.   
	Column name should be exactly the case-sensitive name of the property in the class under model package.
	
