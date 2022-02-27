import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

class Menu {
	private static ArrayList<Data> dataArr = new ArrayList<Data>();
	
	static void loadMenu() {
		System.out.println("MENU");
		System.out.println("1. Insert data");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.print("Isi nomor: ");

        Scanner input = new Scanner(System.in);
    	int numberInput = input.nextInt();

		switch(numberInput) {
			case 1: 
				insertEmployeeData();
				break;
			case 2:
				viewEmployeeData();
				break;
			case 3:
				updateEmployeeData();
				break;
			case 4:
				deleteEmployeeData();
				break;
			default:
				loadMenu();
				break;
		}

		input.close();
	}
	
	private static void deleteEmployeeData() {
		viewData();

		System.out.print("Input nomor urutan yang ingin dihapus: ");

		Scanner dltEmploy = new Scanner(System.in);
		int num = dltEmploy.nextInt();

		if (num < 1 || num > dataArr.size()) {
			deleteEmployeeData();
			dltEmploy.close();
		}

		System.out.println("Karyawan dengan kode " + dataArr.get(num - 1).getKode() + " berhasil dihapus");

		dataArr.remove(num - 1);

		System.out.println("ENTER to return");

		dltEmploy.nextLine();

		loadMenu();

		dltEmploy.close();
	}

	private static void updateEmployeeData() {
		viewData();
		System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");

		Scanner updtEmploy = new Scanner(System.in);
		int update = updtEmploy.nextInt();

		if ((update < 1 && update != 0) || update > dataArr.size()) {
			System.out.println("Input salah");

			loadMenu();

			updtEmploy.close();
		}

		System.out.print("Input nama karyawan [>= 3]: ");

		Scanner myObj = new Scanner(System.in);
		String name = myObj.nextLine();

		if (name.length() < 3 && !name.equals("0")) {
			System.out.println("Input Anda kurang dari 3\nENTER to return");

			myObj.nextLine();
			loadMenu();

			updtEmploy.close();
			myObj.close();
		}

		System.out.print("Input jenis kelamin [Laki - laki | Perempuan] (Case Sensitive): ");

		Scanner mySx = new Scanner(System.in);
		String type = mySx.nextLine();

		if (!type.equals("Laki - laki") && !type.equals("Perempuan")) {
			System.out.println("Input anda tidak sesuai\nENTER to return");

			mySx.nextLine();
			loadMenu();

			updtEmploy.close();
			myObj.close();
			mySx.close();
		}

		System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
		Scanner myClss = new Scanner(System.in);
		String rank = myClss.nextLine();

		if (!rank.equals("Manager") && !rank.equals("Supervisor") && !rank.equals("Admin")) {
			System.out.println("Input anda tidak sesuai\nENTER to return");

			myClss.nextLine();
			loadMenu();

			updtEmploy.close();
			myObj.close();
			mySx.close();
			myClss.close();
		}

		Data newData = new Data();
		String kode =  dataArr.get(update - 1).getKode();

		System.out.println("Berhasil mengupdate karyawan dengan id " + kode);

		if (name.equals("0")) {
			name = dataArr.get(update - 1).getNama();
		}
		
		if (type.equals("0")) {
			type = dataArr.get(update - 1).getJenisKelamin();
		}

		if (rank.equals("0")) {
			rank = dataArr.get(update - 1).getJabatan();
		}

		dataArr.set(update - 1, newData.update(kode, name, type, rank));

		System.out.println("ENTER to return");
		updtEmploy.nextLine();

		loadMenu();

		updtEmploy.close();
		myObj.close();
		mySx.close();
		myClss.close();
	}

	private static void viewEmployeeData() {
		Scanner in = new Scanner(System.in);

		viewData();

		System.out.println("ENTER to return");
		in.nextLine();

		loadMenu();

		in.close();
	}

	private static void viewData() {
		dataArr.sort((o1, o2) -> o1.getNama().compareTo(o2.getNama()));

		System.out.println("No.\tKode\tNama\tJenis Kelamin\tJabatan\t\tGaji");
		for(int i = 0; i < dataArr.size(); ++i) {
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\n", i + 1, dataArr.get(i).getKode(), dataArr.get(i).getNama(), dataArr.get(i).getJenisKelamin(), dataArr.get(i).getJabatan(), dataArr.get(i).getGaji());
		}
	}

	static void insertEmployeeData() {
		System.out.print("Input nama karyawan [>= 3]: ");

        Scanner myObj = new Scanner(System.in);  
        String name = myObj.nextLine();

		if (name.length() < 3) {
			System.out.println("Input Anda kurang dari 3\nENTER to return");

			myObj.nextLine();
			loadMenu();

			myObj.close();
			return;
		}

		System.out.print("Input jenis kelamin [Laki - laki | Perempuan] (Case Sensitive): ");

        Scanner mySx = new Scanner(System.in);  
        String type = mySx.nextLine();  

		if(!type.equals("Laki - laki") && !type.equals("Perempuan")) {
			System.out.println("Input anda tidak sesuai\nENTER to return");

			mySx.nextLine();
			loadMenu();

			myObj.close();
 			mySx.close();
		}

		System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
        Scanner myClss = new Scanner(System.in);
        String rank = myClss.nextLine();

		if (!rank.equals("Manager") && !rank.equals("Supervisor") && !rank.equals("Admin")) {
			System.out.println("Input anda tidak sesuai\nENTER to return");

			myClss.nextLine();
			loadMenu();

			myObj.close();
 			mySx.close();
			myClss.close();
		}

		dataArr.add(new Data(name, type, rank));


		if(dataArr.size() % 3 == 1) {
			for(int i = 0; i < dataArr.size() - 1; ++i) {
				Data newData = new Data().bonusGaji(dataArr.get(i));
				dataArr.set(i, newData);
			}
		}

		System.out.println("Berhasil menambahkan karyawan dengan kode " + dataArr.get(0).getKode());
		System.out.println("ENTER to return");

		myObj.nextLine();
		

		loadMenu();

		myObj.close();
		mySx.close();
		myClss.close();
	}
}