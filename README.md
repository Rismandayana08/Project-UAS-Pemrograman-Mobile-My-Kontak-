# TUGAS AKHIR SEMESTER: APLIKASI "KONTAK SAYA"

**Mata Kuliah**: Pemrograman Mobile Android  
**Dosen Pengampu**: Donny Maulana, S.Kom., M.M.S.I  
**Nama**: Aldi Rismandayana  
**NIM**: 312410015  
**Kelas**: TI.24.A1  


## 1. Deskripsi Aplikasi
Aplikasi **Kontak Saya** adalah aplikasi manajemen kontak pribadi berbasis Android yang dikembangkan menggunakan bahasa pemrograman Java dan Android Studio. Aplikasi ini dirancang dengan mengimplementasikan prinsip Material Design untuk memberikan pengalaman pengguna yang modern, responsif, dan mudah digunakan.

## 2. Fitur Utama
1. **Splash Screen** – Tampilan awal dengan logo dan nama aplikasi.
2. **Loading Screen** – Simulasi proses loading sebelum masuk ke halaman utama.
3. **Daftar Kontak** – Menampilkan kontak dengan indeks huruf (A, B, C, ...) menggunakan RecyclerView dan GridLayoutManager.
4. **Tambah Kontak** – Form input lengkap (nama, nomor, email, alamat, catatan).
5. **Edit Kontak** – Mengubah data kontak yang sudah ada (diakses melalui long click).
6. **Hapus Kontak** – Menghapus kontak dengan konfirmasi (long click → dialog).
7. **Detail Kontak** – Menampilkan informasi lengkap kontak dengan tampilan profesional.
8. **Floating Action Button (FAB)** – Tombol cepat untuk menambah kontak baru.
9. **Navigasi** – Toolbar dengan tombol back dan edit pada halaman detail.

## 3. Teknologi dan Komponen yang Digunakan
- **Bahasa Pemrograman**: Java
- **IDE**: Android Studio (versi terbaru)
- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Library Utama**:
  - AndroidX (AppCompat, Core, RecyclerView, CardView)
  - Material Components (FloatingActionButton, TextInputLayout)
- **Komponen Android**:
  - Activity (Splash, Loading, Permission, ContactList, ContactDetail, AddContact)
  - RecyclerView + CardView untuk daftar kontak
  - Intent untuk navigasi antar activity
  - AlertDialog untuk konfirmasi hapus
  - Serializable untuk passing object Contact antar activity

## 4. Struktur Project
```text
com.example.kontakapp/
├── SplashActivity.java          # Tampilan awal
├── LoadingActivity.java         # Simulasi loading
├── PermissionActivity.java      # (placeholder)
├── ContactListActivity.java     # Halaman utama daftar kontak
├── ContactDetailActivity.java   # Detail kontak
├── AddContactActivity.java      # Tambah & edit kontak
├── Contact.java                 # Model data kontak
├── ContactAdapter.java          # Adapter RecyclerView
res/
├── layout/                      # Semua file XML layout
├── drawable/                    # Icon dan background
├── mipmap/                      # Launcher icon
└── values/                      # Strings, colors, styles
AndroidManifest.xml              # Deklarasi activity & permission
```


## 5. Cara Kerja Aplikasi
1. User membuka aplikasi → SplashActivity (2 detik) → LoadingActivity (3 detik) → ContactListActivity.
2. Di ContactListActivity:
   - Tampilkan dummy data kontak menggunakan RecyclerView.
   - Klik item → buka ContactDetailActivity.
   - Long click item → muncul dialog Edit/Hapus.
   - Klik FAB → buka AddContactActivity untuk tambah kontak baru.
3. Data kontak disimpan sementara di memory (ArrayList) selama aplikasi berjalan.

## 6. Screenshot Aplikasi
*(Lampirkan screenshot pada laporan UAS atau repo GitHub)*
- Splash Screen
- Daftar Kontak dengan indeks A/B
- Detail Kontak
- Form Tambah/Edit Kontak

## 7. Kendala dan Solusi
- **Kendala**: Permission runtime untuk lokasi (jika ditambah fitur), error manifest, icon round tidak ditemukan.
- **Solusi**: Tambah permission di manifest, gunakan `android:exported` sesuai aturan Android 12+, generate launcher icon menggunakan Image Asset.

## 8. Kesimpulan
Aplikasi "Kontak Saya" berhasil dikembangkan dengan fitur CRUD (Create, Read, Update, Delete) dasar menggunakan komponen Android modern. Aplikasi ini telah mengimplementasikan navigasi antar activity, RecyclerView, Material Design, dan manajemen data sementara dengan baik.

## 9. Saran Pengembangan Selanjutnya
- Penyimpanan permanen menggunakan Room Database atau SharedPreferences
- Integrasi dengan kontak sistem Android
- Fitur pencarian kontak
- Tema dark mode
- Sinkronisasi cloud (Firebase)

**Terima kasih atas bimbingan selama semester ini.**

---
Aldi Rismandayana  
312410015  

