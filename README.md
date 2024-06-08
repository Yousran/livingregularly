# Project Management

Project Management adalah aplikasi yang dirancang untuk membantu orang-orang dalam menyusun dan mengatur proyek yang dimiliki, di sertai dengan beberapa fitur tambahan seperti regis dan login, serta ada juga pengatur masa tenggat dan anggaran.

## Group Name : Bismillah Berkah
Nama Anggota :

  1. M. Ervin - H071231050
  2. A.M. Yusran Mazidan - H071231064
  3. Nur Wahida - H071231090

## Tema yang dipilih : Tools And Producitivity
## Nama Pendamping : Muh. Adnan Putra Amiruddin
## Tim Juri :

  1. Kelvin Leonardo Sianipar
  2. Muhammad Ardiansyah Asrifah

## Executive Summary
Project Management adalah aplikasi yang dirancang untuk membantu orang-orang dalam menyusun dan mengatur proyek yang dimiliki, di sertai dengan beberapa fitur tambahan seperti regis dan login, serta ada juga pengatur masa tenggat dan anggaran.

### 1. Latar Belakang
Dalam Kehidupan modern yang dinamis, individu sering kali mengahadapi berbagai proyek pribadi yang memerlukan perencanaan dan pengelolaan yang baik. Meskipun penting, banyak individu merasa kesulitan dalam mengelola proyek-proyek ini dengan efektif.

### 2. Masalah yang diatasi
Banyak catatan yang diperlukan dalam pembuatan sebuah proyek, karena itu kami menyediakan sebuah aplikasi yang memiliki sebuah fitur seperti :

- Login & Register : Kami menyediakan fitur ini agar kami bisa menyakinkan pengguna tentang keamanan data proyek yang sedang dikerjakan.
- Pengatur Waktu : Dengan pengatur waktu ini kami bisa menampilkan sisa waktu yang dibutuhkan pengguna untuk menyelesaikan proyek-proyek yang dimiliki, hal ini bertujuan sebagai pengingat dan agar sang pengguna tidak lalai dalam mengerjakan proyeknya.
- Pengatur Anggaran : Fitur ini dapat mengatur penggunaan anggaran yang dimiliki oleh sang pengguna, jadi pengguna bisa mengetahui berapa banyak sisa anggaran yang dimiliki.

### 3. Alasan Pemilihan Projek ini
Proyek ini dipilih karena :
  Kebutuhan Nyata : Banyak orang kesulitan memprediksi dan mengatur waktu untuk menyelesaikan proyek, sehingga kali mengakibatkan keterlambatan dengan pengeluaran yang tidak terkontrol dapat menyebabkan kekurangan dana di tengah proyek.
  Penggunaan Yang Luas : Dapat menjadi alat yang sangat berguna bagi individu, tim kecil, dan freelancers dalam mengelola proyek.

## Fitur Aplikasi
A. General
  1. *Login User*
  2. *Register User*
  3. *Dashboard*
  4. *Add New Project (projectName, dueDate, Budget, remainingBudget, and addTeam)*
  5. *Log Out*
  6. *Dark Mode*

B. User
  1. Dapat melihat daftar-daftar proyek yang dimiliki dalam dashboard projectList.
  2. Dapat menambahkan proyek baru kedalam projectList.
  3. Dapat melihat sisa anggaran yang dimiliki di detail proyek.
  4. Dapat melihat sisa hari menuju masa tenggat proyek.
  5. Dapat menambahkan teman kedalam tim member yang ada tiap proyek.
  6. Dapat berbagi proyek dengan teman yang telah ditambahkan kedalam member tim proyek.

## Penjelasan Penerapan Prinsip OOP
1. Pada DbConnect.java terdapat class DbConnect sebagai super class
dengan 1 private attribute dan 4 protected attribute. *(encapsulation,
inheritance, polimorfisme)*
2. Pada BudgetController.java terdapat class BudgetController yang
mewarisi DbConnect. *(inheritance)*
3. Pada ProjectController.java terdapat class ProjectController yang
mewarisi DbConnect. *(inheritance)*
4. Pada TeamMemberController.java terdapat class
TeamMemberController yang mewarisi DbConnect. *(inheritance)*
5. Pada UserController.java terdapat class UserController yang
mewarisi DbConnect. *(inheritance)*
6. Pada Model.java terdapat class Model sebagai super class dengan 1
private attribute user_id. *(inheritance)*
7. Pada Budget.java tedapat class Budget yang mewarisi class Model
dan 3 private atttribute. *(inheritance, encapsulation)*
8. Pada Project.java terdapat class Projeect yang mewarisi class Model
dan memiliki 4 private attribute. *(encapsulation, inheritance)*
9. Pada TeamMember.java terdapat class TeamMember yang mewarisi
class Model dan 2 private attribute. *(inheritance, encapsulation)*
10. Pada User.java terdapat class User yang mewarisi class Model dan 4
private attribute. *(inheritance, encapsulation)*
11. Pada CardProject.java terdapat class CardProject yang mewarisi
Button dan 3 private method. *(inheritance, encapsulation)*
12. Pada ComponentNavbar.java terdapat class ComponentNavbar yang
mewarisi Hbox.*(inheritance)*
13. Pada ComponentSidebar.java terdapat class ComponentSidebar
yang mewarisi Vbox dan 1 private attribute serta 4 private
method.*(inheritance, encapsulation)*
14. Pada InterfacePageProject.java terdapat class InterfacePageProject
yang mewariskan ke PageAddProject dan PageProject agar class
yang dimplementasikan memiliki tampilan yang sama dengan class
lain yang mengimplementasikan interface tersebut. *(abstraction)*
15. Pada PageAddProject.java terdapat class PageAddProject yang
mewarisi class Vbox dan class InterFacePageProject dan 1 private
method. *(inheritance, encapsulation)*
16. Pada PageDashboard.java terdapat class PageDashboard yang
mewarisi class Vbox. *(inheritance)*
17. Pada PageProject.java terdapat PageProject yang mewarisi class
Vbox dan class. *(inheritance, encapsulation)*
18. Pada SceneLogin.java terdapat class SceneLogin yang memiliki 7
private attribute dan 1 private method. *(encapsulation)*
19. Pada SceneMain.java terdapat class SceneMain yang memiliki 5
private attribute dan 1 private method. *(encapsulation)*
20. Pada SceneRegister.java terdapat class SceneRegister yang memiliki
9 private attribute dan 1 private method. *(encapsulation)*
21. Pada App.java terdapat class App yang mewarisi classs Aplication.
*(inheritance)*.

## Mentoring
- Kak Adnan - Rabu, 15 Mei 2024
- Kak Adnan – Rabu, 22 Mei 2024
- Kak Adnan – Kamis, 23 Mei 2024

## Screenshots
### *Login Scene*


### *Register Scene*


### *Dashboard*
![Dashboard](https://github.com/Yousran/timetuner/blob/main/documentation/dashboard.jpg)

### *Add New Project*
![Add Project](https://github.com/Yousran/timetuner/blob/main/documentation/add-project.jpg)

### *⁠Project Detail*
![Project Detail](https://github.com/Yousran/timetuner/blob/main/documentation/detail-project.jpg)

### *Dark Mode*
![Dark Mode](https://github.com/Yousran/timetuner/blob/main/documentation/dark-mode.jpg)

⁠ bash
  ./gradlew build
 ⁠
•⁠  ⁠Run App

⁠ bash
  ./gradlew run
 ⁠
    
## Authors

•⁠  ⁠[A.M.Yusran Mazidan](https://www.github.com/yousranmz)
•⁠  ⁠[M. Ervin](https://www.github.com/ervin1809)
•⁠  ⁠[Nur Wahida](https://www.github.com/nurwahida1090)
