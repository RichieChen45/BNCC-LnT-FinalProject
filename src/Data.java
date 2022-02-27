import java.util.Random;

class Data {
	
	private static final int GAJI_MANAGER = 8000000;
	private static final int GAJI_SUPERVISOR = 6000000;
	private static final int GAJI_ADMIN = 4000000;

	private static final double BONUS_GAJI_ADMIN = 0.1;
	private static final double BONUS_GAJI_SUPERVISOR = 0.075;
	private static final double BONUS_GAJI_MANAGER = 0.05;

	private String kode;
	private String nama;
	private String jenisKelamin;
	private String jabatan;
	private int gaji;

	public Data() {}

    public Data(String nama, String jenisKelamin, String jabatan) {
        this.kode = generateCode();
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jabatan = jabatan;

		if (jabatan.equals("Manager")) {
        	this.gaji = GAJI_MANAGER;
		} else if (jabatan.equals("Supervisor")) {
			this.gaji = GAJI_SUPERVISOR;
		} else {
			this.gaji = GAJI_ADMIN;
		}
    }

	public Data(String kode, String nama, String jenisKelamin, String jabatan) {
        this.kode = kode;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jabatan = jabatan;

		if (jabatan.equals("Manager")) {
        	this.gaji = GAJI_MANAGER;
		} else if (jabatan.equals("Supervisor")) {
			this.gaji = GAJI_SUPERVISOR;
		} else {
			this.gaji = GAJI_ADMIN;
		}
    }

	public Data(String kode, String nama, String jenisKelamin, String jabatan, int gaji) {
		this.kode = kode;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.jabatan = jabatan;
		this.gaji = gaji;
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

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
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

	public Data update(String kode, String nama, String jenisKelamin, String jabatan) {
		return new Data(kode, nama, jenisKelamin, jabatan);
	}

	public Data bonusGaji(Data data) {
		if (data.getJabatan().equals("Manager")) {
			int bonusGaji = (int)(data.getGaji() * BONUS_GAJI_MANAGER);
			data.setGaji(data.getGaji() + bonusGaji);
		} else if (data.getJabatan().equals("Supervisor")) {
			int bonusGaji = (int)(data.getGaji() * BONUS_GAJI_SUPERVISOR);
			data.setGaji(data.getGaji() + bonusGaji);
		} else {
			int bonusGaji = (int)(data.getGaji() * BONUS_GAJI_ADMIN);
			data.setGaji(data.getGaji() + bonusGaji);
		}


		return data;
	}
}