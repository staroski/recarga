package br.com.staroski.recarga.persistence;

import br.com.staroski.recarga.persistence.entities.*;

public final class TesteJpa {

	public static void main(String[] args) {
		try {
			new TesteJpa().execute();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private void execute() {
		final Database base = Database.get();
		try {
			base.login("jdbc:hsqldb:file:db/recarga", "sa", "");

			for (Calibre calibre : base.getCalibres()) {
				System.out.println(calibre);
			}

			for (Chumbo chumbo : base.getChumbos()) {
				System.out.println(chumbo);
			}

			for (Espoleta espoleta : base.getEspoletas()) {
				System.out.println(espoleta);
			}

			for (Estojo estojo : base.getEstojos()) {
				System.out.println(estojo);
			}

			for (Polvora polvora : base.getPolvoras()) {
				System.out.println(polvora);
			}

			for (Projetil projetil : base.getProjeteis()) {
				System.out.println(projetil);
			}

			for (Municao municao : base.getMunicoes()) {
				System.out.println(municao + " - " + municao.getCalibre());
			}

			for (Consumo consumo : base.getConsumos()) {
				System.out.println(consumo + " - " + consumo.getMunicao());
			}

			for (Recarga recarga : base.getRecargas()) {
				System.out.println(recarga + " - " + recarga.getMunicao());
			}

		} finally {
			if (base != null) {
				base.logout();
			}
		}
	}
}
