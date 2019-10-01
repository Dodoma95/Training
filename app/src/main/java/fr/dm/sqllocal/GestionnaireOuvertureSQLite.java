package fr.dm.sqllocal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/*
 * Classe Helper pour la gestion de l'ouverture de la BD
 * La connexion a la BD en somme ou la creation et connexion si elle n'existe pas
 * Methodes : constructeur, onCreate, onUpgrade
 */

// -------------------------------------
public class GestionnaireOuvertureSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "cours.db";

    private static final String DROP_TABLE_PAYS = "DROP TABLE IF EXISTS pays";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE IF NOT EXISTS pays(id_pays TEXT PRIMARY KEY, nom_pays TEXT)";

    private static final String DROP_TABLE_VILLES = "DROP TABLE IF EXISTS villes";
    private static final String CREATE_TABLE_VILLES = "CREATE TABLE IF NOT EXISTS villes(id_ville INTEGER PRIMARY KEY AUTOINCREMENT, cp TEXT, nom_ville TEXT, id_pays TEXT, FOREIGN KEY(id_pays) REFERENCES pays(id_pays))";

    // --- Constructeur
    // -------------------------------
    public GestionnaireOuvertureSQLite(Context contexte, CursorFactory fabrique) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(contexte, DB_NAME, fabrique, DB_VERSION);
    } /// GestionnaireOuvertureSQLite()

    @Override
    // -----------------
    public void onCreate(SQLiteDatabase abd) {
        // --- Creation de la table pays
        abd.execSQL(CREATE_TABLE_PAYS);
        // --- Creation de la table villes
        abd.execSQL(CREATE_TABLE_VILLES);

        /*
         * Pour les tests
         */
        // --- Remplissage table pays
        abd.execSQL("INSERT INTO pays(id_pays, nom_pays) VALUES('33','France')");
        abd.execSQL("INSERT INTO pays(id_pays, nom_pays) VALUES('39','Italie')");
        // --- Remplissage table villes
        abd.execSQL("INSERT INTO villes(cp, nom_ville, id_pays) VALUES('75001','Paris I','33')");
        abd.execSQL("INSERT INTO villes(cp, nom_ville, id_pays) VALUES('75011','Paris XI','33')");
        abd.execSQL("INSERT INTO villes(cp, nom_ville, id_pays) VALUES('75012','Paris XII','33')");
        abd.execSQL("INSERT INTO villes(cp, nom_ville, id_pays) VALUES('75020','Paris XX','33')");
    } /// onCreate()



    @Override
    // ------------------
    public void onUpgrade(SQLiteDatabase abd, int ancienneVersion, int nouvelleVersion) {
        // --- Suppression des tables puis appel a la creation des tables
        abd.execSQL(DROP_TABLE_VILLES);
        abd.execSQL(DROP_TABLE_PAYS);
        onCreate(abd);
    } /// onUpgrade()

} /// class GestionnaireOuvertureSQLite
