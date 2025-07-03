package com.example.kpproject.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "time_management")
@Parcelize
data class TimeManagement (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nama_tugas")
    val namaTugas: String,

    @ColumnInfo(name = "deskripsi_tugas")
    val deskripsiTugas: String?, // Bisa null

    @ColumnInfo(name = "kategori")
    val kategori: String, // Penting & Mendesak, Penting & Tidak Mendesak, Tidak Penting & Mendesak, Tidak Penting & Tidak Mendesak

    @ColumnInfo(name = "hari_mulai")
    val hariMulai: String,

    @ColumnInfo(name = "tanggal_mulai")
    val tanggalMulai: String, // Format: YYYY-MM-DD

    @ColumnInfo(name = "jam_mulai")
    val jamMulai: String, // Format: HH:MM

    @ColumnInfo(name = "hari_selesai")
    val hariSelesai: String?, // Bisa null

    @ColumnInfo(name = "tanggal_selesai")
    val tanggalSelesai: String?, // Format: YYYY-MM-DD, bisa null

    @ColumnInfo(name = "jam_selesai")
    val jamSelesai: String?, // Format: HH:MM, bisa null

    @ColumnInfo(name = "status")
    val status: Boolean = false // Status tugas
) : Parcelable