package com.driver;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jDriver {
	
	@Value("${spring.data.neo4j.uri}")  
    private String uri;  
      
    @Value("${spring.data.neo4j.username}")  
    private String username;  
      
    @Value("${spring.data.neo4j.password}")  
    private String password;
    private Driver driver;
    private Session session;
    private Transaction ts;
    
    public Driver getDriver() {
    	Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password) );
    	this.driver = driver;
    	return driver;
    }
	
    public Transaction getTranscation() {
    	getDriver();
    	Session session = driver.session();
    	this.session = session;
    	Transaction ts = session.beginTransaction();
    	this.ts = ts;
    	return ts;
    } 
    
    public void close() {
    	try {
    		if (null != ts) {
    			ts.close();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    }
    
}
