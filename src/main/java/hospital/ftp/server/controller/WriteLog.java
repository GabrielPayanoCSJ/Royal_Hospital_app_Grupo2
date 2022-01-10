package hospital.ftp.server.controller;

import java.time.LocalDate;

import hospital.ftp.model.Log;

public class WriteLog {
	static private LocalDate date;

	static public void upload(String nameUploaded) {
		date = LocalDate.now();
		// escribir en la ventana del servidor
		// escribir en la base de datos
		// formato -> id(autocompletado por la BD) - user_id - group_id -
		// type_ope(Borrado, creación...) - description_ope(ejemplo: borrado de x
		// fichero) - event_date(fecha)
	}

	static public void download(String nameDownloaded) {
		date = LocalDate.now();

		
		
		Log log = new Log(0, 0, 0, "Download", "The file '" + nameDownloaded + "' was downloaded.", date);
	}

	static public void rename(String oldName, String newName) {
		date = LocalDate.now();

	}

	static public void delete(String nameDeleted) {
		date = LocalDate.now();

	}

	static public void create(String nameCreated) {
		date = LocalDate.now();

	}
}
