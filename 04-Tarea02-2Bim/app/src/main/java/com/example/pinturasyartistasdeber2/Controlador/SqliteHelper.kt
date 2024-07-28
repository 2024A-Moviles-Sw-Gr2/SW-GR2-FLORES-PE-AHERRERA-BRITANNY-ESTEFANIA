package com.example.pinturasyartistasdeber2.Controlador


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pinturasyartistasdeber2.Entidades.Artista
import com.example.pinturasyartistasdeber2.Entidades.Pintura

class SqliteHelper(
    context: Context? /* this */
) : SQLiteOpenHelper(context, "AndroidApp", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createArtistaTable = """
            CREATE TABLE Artista (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                fechaNacimiento DATE NOT NULL,
                estaVivo BOOLEAN NOT NULL,
                numObras INTEGER NOT NULL,
                precioPromedio REAL NOT NULL
            );
        """.trimIndent()

        val createPinturaTable = """
            CREATE TABLE Pintura (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            titulo TEXT NOT NULL,
            artistaId INTEGER NOT NULL,
            fechaCreacion DATE NOT NULL,
            esVendida BOOLEAN NOT NULL,
            precio REAL NOT NULL,
            FOREIGN KEY (artistaId) REFERENCES Artista(id)
        );
        """.trimIndent()

        db?.execSQL(createArtistaTable)
        db?.execSQL(createPinturaTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    fun getAllArtistas():ArrayList<Artista>{
        val lectureDB =readableDatabase
        val queryScript ="""
            SELECT * FROM Artista
        """.trimIndent()
        val queryResult = lectureDB.rawQuery(
            queryScript,
            emptyArray()
        )
        val response = arrayListOf<Artista>()
        if (queryResult.moveToFirst()){
            do {
                response.add(
                    Artista(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getString(3).toBoolean(),
                        queryResult.getInt(4),
                        queryResult.getDouble(5)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()
        return response
    }
    fun getPinturasPorArtista(identificador: Int): ArrayList<Pintura> {
        val lectureDB = readableDatabase
        val queryScript = """
        SELECT id, titulo, fechaCreacion, esVendida, precio
        FROM Pintura
        WHERE artistaId=?
    """.trimIndent()
        val queryResult = lectureDB.rawQuery(queryScript, arrayOf(identificador.toString()))
        val response = arrayListOf<Pintura>()

        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    Pintura(
                        queryResult.getInt(0),                // id
                        queryResult.getString(1),             // titulo
                        queryResult.getString(2),             // fechaCreacion
                        queryResult.getString(3).toBoolean(), // esVendida
                        queryResult.getDouble(4)              // precio
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()
        return response
    }


    fun createArtista(nombre:String,
                      fechaNacimiento:String,
                      estaVivo:Boolean,
                      numObras: Int,
                      precioPromedio: Double):Boolean{
        val writeDB = writableDatabase
        val valuesToStore = ContentValues()
        valuesToStore.put("nombre", nombre)
        valuesToStore.put("fechaNacimiento", fechaNacimiento)
        valuesToStore.put("estaVivo", estaVivo)
        valuesToStore.put("numObras",numObras)
        valuesToStore.put("precioPromedio",precioPromedio)


        val storeResult = writeDB.insert(
            "Artista",
            null,
            valuesToStore
        )
        writeDB.close()

        return storeResult.toInt() !=-1
    }
    fun createPintura(
        titulo:String,
        artistaId:Int,
        fechaCreacion:String,
        esVendida:Boolean,
        precio:Double,
    ):Boolean{
        val writeDB = writableDatabase
        val valuesToStore = ContentValues()
        valuesToStore.put("titulo",titulo)
        valuesToStore.put("artistaId",artistaId)
        valuesToStore.put("fechaCreacion",fechaCreacion)
        valuesToStore.put("esVendida",esVendida)
        valuesToStore.put("precio",precio)

        val storeResult = writeDB.insert(
            "Pintura",
            null,
            valuesToStore
        )
        writeDB.close()

        return storeResult.toInt() !=-1
    }

    fun updateArtista(
        id: Int,
        precioPromedio: Double
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues()
        valuesToUpdate.put("precioPromedio", precioPromedio)

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "Artista", // Table name
            valuesToUpdate,
            "id=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    //TODO
    fun updatePintura(
        id: Int,
        precio: Double
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues()
        valuesToUpdate.put("precio", precio)

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "Pintura", // Table name
            valuesToUpdate,
            "id=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    fun deleteArtista(id:Int): Boolean {
        val writeDB = writableDatabase
        // SQL query example: where .... ID=? AND NAME=? [?=1, ?=2]
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "Artista",
            "id=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }

    fun deletePintura(id:Int): Boolean {
        val writeDB = writableDatabase
        // SQL query example: where .... ID=? AND NAME=? [?=1, ?=2]
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "Pintura",
            "id=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }

}
