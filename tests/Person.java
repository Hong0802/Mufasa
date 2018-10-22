import static org.junit.Assert.*;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;

public class Person {
	MufasaAccountCreation mfs;
	MufasaAddress userAddress;
	MufasaBankACC userBank;
	MufasaTransaction transaction;
	Transaction info;
	
	@Before
	public void setup(){
		info = new Transaction();
		
		mfs = EasyMock.createMock(MufasaAccountCreation.class);
		info.setAccount(mfs);
		
		userAddress = EasyMock.createMock(MufasaAddress.class);
		info.setAddress(userAddress);
		
		userBank = EasyMock.createMock(MufasaBankACC.class);
		info.setBank(userBank);

	} 

	@Test
	public void test() throws UserException, ParseException, AddressException, BankAccountException {
		//User
		Mufasa newUser = new Mufasa();
		
		newUser.setFirstName("testFirstName");
		newUser.setLastName("testLastName");
		newUser.setPassword("TestPassword", "TestPassword");
		newUser.setUsername("testUsername");
		newUser.setPhoneNumber("1231311");
		newUser.setBirthdate("01/01/1990");
		newUser.setCountry("Finland");
		newUser.setEmail("TEST@TEST.COM", "TEST@TEST.COM");
		
		EasyMock.expect(mfs.getAccInfo()).andReturn(newUser);
		EasyMock.replay(mfs);
		
		//Address
		Address newAddress = new Address();
		
		newAddress.setStreetAddress("testAddress1");
		newAddress.setCity("Oulu");
		newAddress.setPostalCode("90570");
		newAddress.setCountry("finland");
		
		EasyMock.expect(userAddress.getAddressInfo()).andReturn(newAddress);
		EasyMock.replay(userAddress);
		
		//Bank Account
		BankAccount newBank = new BankAccount();
		
		newBank.setUserAddress(newAddress);
		newBank.setBankAccountPassword("1234asdf", "1234asdf");
		newBank.setCardHolderName("testHolderName");
		newBank.setCardNumber("1235315678952354");
		newBank.setExpiryDate("09/20");
		
		EasyMock.expect(userBank.getBankInfo(newUser)).andReturn(newBank);
		EasyMock.replay(userBank);
		
		transaction = EasyMock.createMock(MufasaTransaction.class);
		EasyMock.expect(transaction.getTransactionResult()).andReturn(1);
		
		String result = info.getTransactionResult();
		
		assertEquals("Transaction Passed", result);
		}

}
