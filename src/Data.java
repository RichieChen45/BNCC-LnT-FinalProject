import java.util.Random;

class Data {

	private String kode;
	private String nama;
	private int harga;
	private int stok;

	public Data() {}

    public Data(String nama) {
        this.kode = generateCode();
        this.nama = nama;
    }

	public Data(String kode, String nama, int harga, int stok) {
		this.kode = kode;
		this.nama = nama;
		this.harga = harga;
		this.stok = stok;
	}

	private String generateCode(){
		String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numList = "1234567890";

		int strLen = 2;
		int numLen = 4;

		StringBuffer randomString = new StringBuffer();

		for(int i = 0; i < strLen; ++i) {
			int number = getRandomNumber(charList);
			char ch = charList.charAt(number);
			randomString.append(ch);
		}

		randomString.append("-");

		for(int i = 0; i < numLen; ++i) {
			int number = getRandomNumber(numList);
			char ch = numList.charAt(number);
			randomString.append(ch);
		}

		return randomString.toString();
    }
 
	private int getRandomNumber(String list) {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(list.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

	public Data update(String kode, String nama, int harga, int stok) {
		return new Data(kode, nama, harga, stok);
	}
}