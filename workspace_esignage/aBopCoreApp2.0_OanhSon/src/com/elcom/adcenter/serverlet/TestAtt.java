package com.elcom.adcenter.serverlet;

import javacryption.aes.AesCtr;

import com.elcom.adcenter.common.Param;

public class TestAtt {
	public static void main(String arg[])
	{
		String xml = "Iv6b7IK1nlLKBpTXSEaeFNEooLKGZYRNQHu/XMMpeMkouSGdiQ0Q+zvTmAWCzql1tXzpKmbn8dwhCgvjDi4kAmu/1jRqY+j8uIy2+dj/QzhO/R3rcmOjLRpf1Qlq9PXhNh+zc5MyK5es5RttXZJKNzSVvdQuUJjVpQ72OuCQJiPdW4xfVgcj6ATBeBM+Bgm7CrwXIw35hN8k+Pma1zctqtqMYrlHcg+2UD2mrsDd6cv443TR+64MhJrlfTH0KMsbR4EFXpxj+3OsChseNrSZdDrdrfB8iluPKmdgzXG1sfw9hSxyoCVkpwr1ToLwvgFNEypnodhR89dnFp6QntuJyg6X44oOxH1l2xRICxZVqIbz4ydzlw4t825q7cQzehaRLfLOmADctBoXQMpi+hjUeSkLf22YfrCLKi+3VR6EZOeU88OyUwykBFH0l+HOjO270DCAN+WR9s271SiU2H7iazk5BDBVWX+AkBL8NmDmttuVpPLpBNgiFCU6yiFpS0rdwGWOtnZlLoh7Im86k1BNzncAqz9RvI8UPm5SOn74I+eT8Q7zbsexw+Hn89XSh77q6YqOm72bLqMJgCVQTFph7IpFuz9Qg6Wo5DBT2Q==";
		String xml1 = AesCtr.decrypt(xml, Param.key, 256);
		
		System.out.println(xml1);
	}
}
