package com.project.auth;

public class JwtConfig {
	
	public static final String RSA_PRIVADA = "\r\n" + 
			"-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEA589MSYesImDKAxbusr25WgIl4LbTOUkxS9xMZt6qb2eIG88I\r\n" + 
			"JDa8Arg9Do/PoHVLEp00m/Ic6QKffjGZWo0tSNveXEKae0UW+laczWLXN284plEw\r\n" + 
			"k95axaKRA0EUKQAU6lkjpeKbE1FAT6ne4P8voSpdkGixTT+oNfAKDaXIBxirzNY7\r\n" + 
			"X1BU74nkftpTaju1o75ZxOsY1oY5tN9+TitDQoIHAeslh9G7AAvr5ABIL14RpIy/\r\n" + 
			"4jcOiDgq0tpRa9jat5/m1lpL/K81mvMyK9swzjAnlmBs274Czod00qqWB+BC9s3o\r\n" + 
			"CewlcGEjBLOSJP/Vbb4qaHvf6EihOnG8WXFvswIDAQABAoIBAQCskTHle061uQAq\r\n" + 
			"FKRWA888JmU0SGYuO0KrirCF1DVlAdnded5Bt013aNhrQrv6Y9cjYEyN20uSWxWI\r\n" + 
			"yDu1oWuY1bVPUXXXVrOoYVoRvAcjiXTOu5pCOkGw5zi1B3mOnDT10K4tC+kOFGqf\r\n" + 
			"44G8VbyLahKFMqrrKvx6StvxFP036ccNy+0IWts5df22xIV3e5q+mdsWXUHcyshG\r\n" + 
			"fJ5HYdqu11CCx4wSEEM0CDuE4Sa/ah9tYCIGwHUb+m1j/1kNbEAkRcy5Tuj7XjXk\r\n" + 
			"9bdMRri2oOyweInYWlaXy9AxGLf8lTAmk75pd4dlmMAI8RRS/XOSRyCTwJFN9AXl\r\n" + 
			"txfpyvDhAoGBAPl4boC6EfSbBguuOolsfC1vQ+NguncuZOS3Lq7FLnlNNBAmlNHP\r\n" + 
			"jU36FNPRHhUaljGher8M703nDSB/jZ2r3SH4YFVPAfrjRrZ5xpVXsBj04ddIPLtE\r\n" + 
			"oy/siwhTHJZhqIKUVt/ceYGWKyGFdwST8hO9kXVy2xkyfce1xEGvnFyXAoGBAO3g\r\n" + 
			"iBQz/SU5XsOcv8uw9zQcx2X/e+q/+FXYSLPPFtiaz73BoCvqqNm4+zsuoJHSTgU0\r\n" + 
			"68PNu3baDNSPeHVN5woeJjrUKnG+7gqgEwwETlP+3gGhy1Gf+2i7OmdZ6sNnmiSb\r\n" + 
			"czTwsjXi5kgtpbiX3aMLxsU7TgMPgyx/rVg8t71FAoGAT/XoBV6ejrZ5s5i+rnmV\r\n" + 
			"O8LkEadZXQYD1MNaNS6CfOLgAyU6oh1Sf2He9x2Z9M22wmWtwDrXITMjo/z+WUFS\r\n" + 
			"+aZwyicuzUm3ypkxq4CVswoasTQ4MxYG8D9zONHa4eCfcGs3hT1XvM9zN6Vdxpvd\r\n" + 
			"BbLHQsvzqhni8vYx15QAHDUCgYEA0do7MleLA8PQ7hDiwvejnb2lyz3QPBCPLGxZ\r\n" + 
			"9wbCyOQKUQT4IMYrn6epO1SvIvW9DqrZMyvULc2/c+X+Br5rDGn8n/l8WExwA7+k\r\n" + 
			"Df3efAtkH1x02dWhHYvOwk545VoIijL5N8L/9LnvHc3nXSncxXImMOlYsSqGNkOM\r\n" + 
			"0UL/dWECgYAL+80dv72q4SECwhaxAQw/+a8Rtnr970l9awvwAwButst/VBleFjud\r\n" + 
			"mibSe5f0f+hD7NMMl0qwulPJc58KunZhDGIygcY3YyH/iKnAu/brC/W0STEuxuhS\r\n" + 
			"HtHbb3X4DnHz5LjGMxudMB1WpUlm8YtiHhNVCRVit1sUrh3pzSTwPQ==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA589MSYesImDKAxbusr25\r\n" + 
			"WgIl4LbTOUkxS9xMZt6qb2eIG88IJDa8Arg9Do/PoHVLEp00m/Ic6QKffjGZWo0t\r\n" + 
			"SNveXEKae0UW+laczWLXN284plEwk95axaKRA0EUKQAU6lkjpeKbE1FAT6ne4P8v\r\n" + 
			"oSpdkGixTT+oNfAKDaXIBxirzNY7X1BU74nkftpTaju1o75ZxOsY1oY5tN9+TitD\r\n" + 
			"QoIHAeslh9G7AAvr5ABIL14RpIy/4jcOiDgq0tpRa9jat5/m1lpL/K81mvMyK9sw\r\n" + 
			"zjAnlmBs274Czod00qqWB+BC9s3oCewlcGEjBLOSJP/Vbb4qaHvf6EihOnG8WXFv\r\n" + 
			"swIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
