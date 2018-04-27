# hibernate
sample code for hibernate

#### Advantage of Hibernate 
	1. Easy to use multiple database. 
		- Just add another config file and load.
		
#### Steps to configure multiple database
	1. Add configuration file under 'config' folder and set database information
		- Refer to ./hibernate/config/hibernate.cfg.xml
	2. In ./hibernate/src/sophia/hibernate/manager/SessionManager class, add or edit method that handles configuration file.
		- Add method named getSessionFactory${seq}
		- Edit method named closeSessionFactory()
	3. Call different sessionFactory in TestMain class
		- You can open multiple SessionFactory
		- To execute query, just send the sessionFactory as a parameter while calling the CRUD methods.
	