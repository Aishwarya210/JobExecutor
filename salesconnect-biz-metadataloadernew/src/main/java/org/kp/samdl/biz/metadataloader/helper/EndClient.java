package org.kp.samdl.biz.metadataloader.helper;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class EndClient {
	public static void main(String args[]) throws SolrServerException, IOException { 
	      //Preparing the Solr client 
	      String urlString = "http://cskpcloudxn2901.cloud.kp.org:8984/solr/#/CommunityLibCollection/documents"; 
	      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   
	      
	       
	      SolrInputDocument doc = new SolrInputDocument(); 
	   
	      UpdateRequest updateRequest = new UpdateRequest();  
	      updateRequest.setAction( UpdateRequest.ACTION.COMMIT, false, false);    
	      SolrInputDocument myDocumentInstantlycommited = new SolrInputDocument();  
	      
	      myDocumentInstantlycommited.addField("document_id", "101"); 
	      myDocumentInstantlycommited.addField("account_name", "Rahman"); 
	      myDocumentInstantlycommited.addField("account_number","27"); 
	      myDocumentInstantlycommited.addField("region","hyderabad");
	      myDocumentInstantlycommited.addField("year","2022");
	      myDocumentInstantlycommited.addField("filename","Account");
	      myDocumentInstantlycommited.addField("url","https://google.com");
	      myDocumentInstantlycommited.addField("filesize","100kb");
	      myDocumentInstantlycommited.addField("filetype","csv");
	      myDocumentInstantlycommited.addField("filefrom","Opportunity");
	      myDocumentInstantlycommited.addField("created_by","955006");
	      myDocumentInstantlycommited.addField("created_date","07-10-2022");
	      myDocumentInstantlycommited.addField("updated_by","955006");
	      myDocumentInstantlycommited.addField("updated_date","07-10-2022");
	      
	      
	      
	      updateRequest.add( myDocumentInstantlycommited);  
	      UpdateResponse rsp = updateRequest.process(Solr);
	      System.out.println("Documents Updated");
	   } 
	}

