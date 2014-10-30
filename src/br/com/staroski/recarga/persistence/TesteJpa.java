package br.com.staroski.recarga.persistence;

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

		} finally {
			if (base != null) {
				base.logout();
			}
		}
	}
}
