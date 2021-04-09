package org.apache.commons.mail;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Test;
import org.subethamail.smtp.server.Session;
import java.util.Date;

public class EmailTest {
	
	private static final String[] TestEmails = {"ab@bc.com", "a.b@c.org", "test@gmail.com"};

	private EmailConcrete email;
	@Before
	public void setUpEmailTest() throws Exception {
		
		email = new EmailConcrete();
		
	}
	
	@After
	public void tearDownEmailTest() throws Exception 
	{
		
	}
	
	@Test
	public void testaddBcc() throws Exception
	{
		email.addBcc(TestEmails);
		assertEquals(3, email.bccList.size());
		assertEquals(3, email.getBccAddresses().size());
	}
	
	@Test
	public void testaddCc() throws Exception
	{
		email.addCc(TestEmails);
		assertEquals(3, email.bccList.size());
		assertEquals(3, email.getBccAddresses().size());
	}
	
	@Test
	public void testaddHeader() throws Exception
	{
		email.addHeader("Test Header", "1");
		
		if (email.headers == null)
			throw new EmptyStackException();
		
		assertEquals(1, email.headers.size());
		
		if (!email.headers.containsKey("Test Header") || !email.headers.containsValue("1"))
			throw new EmptyStackException();
	}
	
	@Test
	public void testaddReplyTo() throws Exception
	{
		email.addReplyTo("test@gmail.com");
		if (!email.replyList.contains("test@gmail.com"))
			throw new EmptyStackException();
	}
	
	@Test
	public void testbuildMimeMessage() throws Exception
	{
		email.buildMimeMessage();
		if (email.getMimeMessage() == null)
			throw new EmptyStackException();
	}

	@Test
	public void testgetHostName() throws Exception
	{
		String HostName = email.getHostName();
		if (HostName.length() == 0)
			throw new EmptyStackException();
	}
	
	@Test
	public void testgetMailSession() throws Exception
	{
		javax.mail.Session se = email.getMailSession();
		
		if (se.getProperties() == null || se == null)
			throw new EmptyStackException();
		
		
		assertEquals(email.smtpPort, se.getProperty(EmailConstants.MAIL_PORT));
		assertEquals(email.hostName, se.getProperty(EmailConstants.MAIL_HOST));
		assertEquals(String.valueOf(email.debug), se.getProperty(EmailConstants.MAIL_DEBUG));
	}
	

	@Test
	public void testgetSentDate() throws Exception
	{
		Date date = email.getSentDate();
		
		if (date == null)
			throw new EmptyStackException();
	}
	

	@Test
	public void testgetSocketConnectionTimeout() throws Exception
	{
		int timeout = email.getSocketConnectionTimeout();
		if (timeout == 0)
			throw new EmptyStackException();
	}

	@Test
	public void testsetFrom() throws Exception
	{
		Email em = email.setFrom("test@gmail.com");
		if(em == null)
			throw new EmptyStackException();
	}
	
}
