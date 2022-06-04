import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

class Menu {
	private static ArrayList<Data> dataArr = new ArrayList<Data>();
	
	static void loadMenu() {
		System.out.println("MENU PT Pudding");
		System.out.println("1. View Menu");
		System.out.println("2. Update Memu");
		System.out.println("3. Delete Menu");
		System.out.println("4. Tabel Menu");
		System.out.print("Isi nomor: ");

        Scanner input = new Scanner(System.in);
    	int numberInput = input.nextInt();

		switch(numberInput) {
			case 1: 
				insertMenuData();
				break;
			case 2:
				viewMenuData();
				break;
			case 3:
				updateMenuData();
				break;
			case 4:
				deleteMenuData();
				break;
			default:
				loadMenu();
				break;
		}
		input.close();
	}
	
	private static void deleteMenuData() {
		viewData();

		System.out.print("Input nomor urutan yang ingin dihapus: ");

		Scanner dltMenu = new Scanner(System.in);
		int num = dltMenu.nextInt();

		if (num < 1 || num > dataArr.size()) {
			deleteMenuData();
			dltMenu.close();
		}

		System.out.println("Menu dengan kode " + dataArr.get(num - 1).getKode() + " berhasil dihapus");

		dataArr.remove(num - 1);

		System.out.println("ENTER to return");

		dltMenu.nextLine();

		loadMenu();

		dltMenu.close();
	}

	private static void updateMenuData() {
		viewData();
		System.out.print("Input nomor urutan menu yang ingin diupdate: ");

		Scanner updtMenu = new Scanner(System.in);
		int update = updtMenu.nextInt();

		if ((update < 1 && update != 0) || update > dataArr.size()) {
			System.out.println("Input salah");

			loadMenu();

			updtMenu.close();
		}

		System.out.print("Input nama menu [>= 3]: ");

		Scanner myObj = new Scanner(System.in);
		String name = myObj.nextLine();

		if (name.length() < 3 && !name.equals("0")) {
			System.out.println("Input Anda kurang dari 3\nENTER to return");

			myObj.nextLine();
			loadMenu();

			updtMenu.close();
			myObj.close();
		}

		Data newData = new Data();
		String kode =  dataArr.get(update - 1).getKode();

		System.out.println("Berhasil mengupdate menu dengan id " + kode);

		if (name.equals("0")) {
			name = dataArr.get(update - 1).getNama();
		}

		dataArr.set(update - 1, newData.update(kode, name, harga, stok));

		System.out.println("ENTER to return");
		updtMenu.nextLine();

		loadMenu();

		updtMenu.close();
		myClss.close();
	}

	private static void viewMenuData() {
		Scanner in = new Scanner(System.in);

		viewData();

		System.out.println("ENTER to return");
		in.nextLine();

		loadMenu();

		in.close();
	}

	private static void viewData() {
		dataArr.sort((o1, o2) -> o1.getNama().compareTo(o2.getNama()));

		System.out.println("No.\tKode\tNama\tHarga\tStok");
		for(int i = 0; i < dataArr.size(); ++i) {
			System.out.printf("%d\t%s\t%s\t%d\t%d\n", i + 1, dataArr.get(i).getKode(), dataArr.get(i).getNama(), dataArr.get(i).getHarga(), dataArr.get(i).getStok());
		}
	}

	static void insertMenuData() {
		System.out.print("Input nama menu [>= 3]: ");

        Scanner myObj = new Scanner(System.in);  
        String name = myObj.nextLine();

		if (name.length() < 3) {
			System.out.println("Input Anda kurang dari 3\nENTER to return");

			myObj.nextLine();
			loadMenu();

			myObj.close();
			return;
		}

		dataArr.add(new Data(name));

		if(dataArr.size() % 3 == 1) {
			for(int i = 0; i < dataArr.size() - 1; ++i) {
				Data newData = new Data();
				dataArr.set(i, newData);
			}
		}

		System.out.println("Berhasil menambahkan menu dengan kode " + dataArr.get(0).getKode());
		System.out.println("ENTER to return");

		myObj.nextLine();
		
		loadMenu();

		myObj.close();
	}
}