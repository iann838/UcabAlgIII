package edu.ucab.cryptomonitor.deprecated;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

import edu.ucab.cryptomonitor.models.User;
import java.util.ArrayList;

public class MainDepre {

	public static String email = "", password = "", audioFile = "";
	public static int option = -1, optionAlert = -1;
	public static boolean activate = false;
	public static ArrayList<Alert> alerts = new ArrayList<Alert>();
	public static int j = 0;

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		Scanner scanner = new Scanner(System.in);
		User user = new User();
//					File file = null;	

		do {
			Menu();

			System.out.println("");
			System.out.print("\n Por favor, introduzca una opción : ");
			option = validateOption(option);
			System.out.println("");

			switch (option) {

			/*
			 * case 1:
			 * 
			 * System.out.println (" Ha seleccionado la opcion N ° 1 - Registrarse \n");
			 * user = user.registerUser(user);
			 * 
			 * if ( ( user.getEmail().equals("ejemplo@gmail.com") ) && (
			 * user.getPassword().equals("UcabJ1#@#8") ) ) { System.out.println
			 * ("\n No puede emplear los ejemplos como datos de acceso ");
			 * System.out.println ("\n Proceso de registro denegado "); }
			 * 
			 * else {
			 * 
			 * if ( ( user.verifyEmail(user.getEmail()) == true ) && (
			 * user.validatePassword(user.getPassword()) == true ) ) { System.out.println
			 * ("\n Proceso de registro exitoso "); activate = true; }
			 * 
			 * else { System.out.println
			 * ("\n El correo o la contraseña no cumplen con las restrincciones de formato"
			 * ); System.out.println ("\n Proceso de registro denegado "); user = new User
			 * (); } } break;
			 * 
			 * case 2: System.out.println
			 * (" Ha seleccionado la opcion N ° 2 - Autenticarse: \n");
			 * 
			 * if ( activate != false ) {
			 * 
			 * User authenticatedUser = new User (user.getEmail(),user.getPassword());
			 * 
			 * 
			 * System.out.print ("\n Introduzca su correo de acceso: "); email =
			 * scanner.nextLine();
			 * 
			 * System.out.print ("\n Introduzca su contraseña de acceso: "); password =
			 * scanner.nextLine();
			 * 
			 * User loginUser = new User (email,password);
			 * 
			 * if (user.authenticateUser(loginUser, authenticatedUser) == true )
			 * System.out.println ("\n El usuario ha sido autenticado "); // Ingresar area
			 * privada } else {
			 * 
			 * System.out.print ("\n Proceso de autenticación fallido "); // Mostrar solo
			 * bitcoin
			 * 
			 * option = -1;
			 * 
			 * System.out.print ("\n ¿ Qué desea hacer ? "); System.out.print
			 * ("\n \t * 1. Registarse:  "); System.out.print
			 * ("\n \t * 0. Salir del programa:  "); option = validateOption (option);
			 * 
			 * do {
			 * 
			 * if (option == 1) { System.out.println (" Ha presionado 1 - Registrarse \n");
			 * user = user.registerUser(user); }
			 * 
			 * 
			 * if (option == 0) System.out.println
			 * (" Ha presionado 0 - Salir del programa \n"); System.exit(option);
			 * 
			 * } while ( (option != 0) || (option != 1) );
			 * 
			 * 
			 * 
			 * }
			 * 
			 * 
			 * break;
			 */

			case 3:
				break;

			case 4:
				break;

			case 5:

				System.out.println(" Ha seleccionado la opcion N ° 5 - Alertas: \n");
				System.out.println(" ¿ Qué desea hacer ?: \n");

				do {

					alertMenu();

					optionAlert = validateOption(optionAlert);

					switch (optionAlert) {

					case 1:
						System.out.println(" Usted ha escogido 1 - Crear alerta  \n");
						float i;
						File file = null;
						String tono = "";
						String crypto = "";
						System.out.println(" Indique el precio en el que se activara la alerta: \n");
						i = scanner.nextFloat();
						System.out.println(" Indique la criptomoneda que quiere monitorear: \n");
						scanner.nextLine();
						System.out.println(" Seleccione el tono que desea usar:   \n");
						tono = defineAlert(file, tono);

						crypto = scanner.nextLine();
						if (tono != "") {
							file = saveAlert(file, tono);
							System.out.println(" Alerta guardada \n ");
							Alert alert = new Alert(file, i, crypto);
							alerts.add(alert);
						}
						break;

					case 2:
						System.out.println(" Usted ha escogido 2 - Eliminar alerta  \n");
						System.out.println(" Indique el numero de la alerta que desea eliminar");
						int k = scanner.nextInt();
						if (k >= 0 && k <= alerts.size()) {
							alerts.remove(k - 1);
						} else {
							System.out.println(" No hay una alarma creada en esa posicion \n");
						}

						break;

					case 3:
						System.out.println(" Usted ha escogido 3 - Modificar alerta  \n");
						String tono1 = "";
						File file1 = null;
						System.out.println(" Indique el numero de la alerta que desea modificar");
						k = scanner.nextInt();
						scanner.nextLine();
						if (k >= 0 && k <= alerts.size()) {
							System.out.println(" Indique el nuevo tono: ");
							tono1 = defineAlert(file1, tono1);
							System.out.println(" Indique nuevo precio: ");
							i = scanner.nextFloat();
							System.out.println(" Indique la criptomoneda que quiere monitorear: \n");
							scanner.nextLine();
							crypto = scanner.nextLine();
							if (tono1 != "") {
								file1 = saveAlert(file1, tono1);
								System.out.println(" Alerta guardada \n ");
								Alert alert = new Alert(file1, i, crypto);
								alerts.set(k - 1, alert);

							}

						} else {
							System.out.println(" No hay una alarma creada en esa posicion \n");
						}

						break;

					case 4:
						System.out.println(" Usted ha escogido 4 - Consultar alerta  \n");

						if (alerts.size() != 0) {
							int n = 1;
							for (Alert alertas : alerts) {

								System.out.println(n++ + ")");
								System.out.println(" Tono: " + alertas.getFile().getName());
								System.out.println(" Precio: " + alertas.getPrice());
								System.out.println(" Critomoneda: " + alertas.getCrypto());
							}
						} else {
							System.out.println(" No hay alertas creadas.");
						}
						break;

					case 5:
						int h = scanner.nextInt();
						scanner.nextLine();
						if (h >= 0 && h <= alerts.size()) {
							File file2 = null;
							String play = alerts.get(h).getFile().getName();
							setAlert(file2, play);
						}

						break;

					case 0:
						System.out.println(" Usted ha escogido 0 - Regresar al Menu Principal  \n");
						break;

					default:
						System.out.println(" Opción no válida o no contemplada en el menu ");
						break;

					}

					System.out.println("");
					pauseScreen();

				} while (optionAlert != 0);

				break;

			case 0:
				System.out.println(" Ha seleccionado la opcion N ° 0 - Salir del programa : \n");
				break;

			default:
				System.out.println(" Opción no válida o no contemplada en el menu ");
				break;
			}

			pauseScreen();
			// cls();

		} while (option != 0);

	}

	// User user = new User ("testuser", "a@b.c", "test123");
	// user.save();

	// System.out.println(User.authenticate("testuser", "test123"));

	/* Methods */

	private static void Menu()

	{
		System.out.println("   ******************************************************   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *         ->     Monitor de Bitcoin                  *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     1.  Registrarse                                *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     2.  Iniciar seccion                            *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     3.  Modificar datos de acceso                  *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     4.  Mostrar top 10 bitcoins de CoinMarketCap   *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     5.  Alertas                                    *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     0.  Salir del programa                         *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   ******************************************************   ");
	}

	private static void alertMenu()

	{
		System.out.println("   ******************************************************   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *         ->           ALERTAS                       *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     1.  Crear alerta                               *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     2.  Eliminar alerta                            *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     3.  Modificar alerta                           *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     4.  Consultar alerta                           *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   *     0.  Regresar al menu principal                 *   ");
		System.out.println("   *                                                    *   ");
		System.out.println("   ******************************************************   ");
	}

	private static void pauseScreen() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n Pulse cualquier tecla para continuar... \n");
		scanner.nextLine();

	}

	public static void cls() {

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception E) {
			System.out.println(E);
		}
	}

	private static int validateOption(int option) {

		Scanner scanner = new Scanner(System.in);

		while (!scanner.hasNextInt()) {
			scanner.next();
			System.out.print("\n Entrada invalida: \n");
		}
		option = scanner.nextInt();

		return option;
	}

	/* Alert Methods */

	private static String defineAlert(File file, String audioFile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		int option = -1;

		System.out.println(" 1. Moneda de Mario Bros ");
		System.out.println(" 2. Caja registradora ");
		System.out.println(" 3. Arpa ");
		System.out.println(" 4. Campana ");
		System.out.println(" 5. ¡ Aquí viene el dinero ! ");
		System.out.println(" 6. Disparo ");
		System.out.println(" 0. Regresar al menu de alertas");

		option = validateOption(option);

		switch (option) {

		case 1:
			System.out.println(" Alerta 1: Moneda de Mario Bros \n");
			audioFile = "media/Mario_Bros_Coin.wav";
			setAlert(file, audioFile);
			break;

		case 2:
			System.out.println(" Alerta 2: Caja registradora \n ");
			audioFile = "media/Cash_register.wav";
			setAlert(file, audioFile);
			break;

		case 3:
			System.out.println(" Alerta 3: Arpa \n ");
			audioFile = "media/Dreaming_Harp.wav";
			setAlert(file, audioFile);
			break;

		case 4:
			System.out.println(" Alerta 4: Campana \n ");
			audioFile = "media/Bell.wav";
			setAlert(file, audioFile);
			break;

		case 5:
			System.out.println(" Alerta 5: ¡ Aquí viene el dinero ! \n ");
			audioFile = "media/Here_comes_the_money.wav";
			setAlert(file, audioFile);
			break;

		case 6:
			System.out.println(" Alerta 6: Disparo \n ");
			audioFile = "media/Big_Shot.wav";
			setAlert(file, audioFile);
			break;

		case 0:
			System.out.println(" Regresando al menu de alertas \n ");
			if (file == null) {
				audioFile = "";
			}

			break;

		default:
			System.out.println(" Opción no válida o no contemplada en el menu. Regresando al menu de alertas ");
			break;

		}

		return audioFile;

	}

	private static void setAlert(File file, String audioFile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		String response = "";
		Scanner scanner = new Scanner(System.in);

		file = new File(audioFile);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);

		while (!response.equals("S"))

		{
			System.out.println("\t Reproducir alerta = R ");
			System.out.println("\t Detener alerta = D ");
			System.out.println("\t Salir - Regresar al menu de alertas = S ");

			response = scanner.next().toUpperCase();

			switch (response)

			{
			case ("R"):
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				break;

			case ("D"):
				clip.stop();
				break;

			case ("S"):
				clip.stop();
				System.out.println(" Regresando al menu de alertas \n ");
				break;

			default:
				System.out.println(" Respuesta no valida \n");
				break;
			}
		}

	}

	private static File saveAlert(File file, String audioFile) {
		return file = new File(audioFile);
	}

	private static File deleteAlert(File file) {
		return file = null;
	}

	private static String requestAlert(File file) {

		String nameAlert = "";

		if (file.toString() == "Mario Bros Coin.wav") {
			nameAlert = "Moneda de Mario Bros";
		}

		if (file.toString() == "Cash register.wav") {
			nameAlert = "Caja registradora";
		}

		if (file.toString() == "Dreaming Harp.wav") {
			nameAlert = "Arpa";
		}

		if (file.toString() == "Bell.wav") {
			nameAlert = "Campana";
		}

		if (file.toString() == "Here comes the money.wav") {
			nameAlert = "¡ Aquí viene el dinero !";
		}

		if (file.toString() == "Big Shot.wav") {
			nameAlert = "Disparo";
		}

		return (" La alerta actual es: " + nameAlert + " \n");
	}

}