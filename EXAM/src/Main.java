import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class OnlineTest extends JFrame implements ActionListener {
    JLabel l, timerLabel;
    JRadioButton jb[] = new JRadioButton[5];
    JButton b1, b2;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[20];
    Timer timer;

    // Array to store user's answers
    char userAnswers[] = new char[20];

    OnlineTest(String s) {
        super(s);
        getContentPane().setBackground(new Color(255, 127, 0));
        l = new JLabel();
        add(l);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Next");
        b2 = new JButton("Bookmark");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();

        // Adding timer
        timerLabel = new JLabel();
        timerLabel.setBounds(450, 20, 100, 30);
        add(timerLabel);
        int delay = 1000;
        ActionListener taskPerformer = new ActionListener() {
            int timeRemaining = 600; // 10 minutes

            public void actionPerformed(ActionEvent evt) {
                if (timeRemaining > 0) {
                    timerLabel.setText("Time: " + timeRemaining + "s");
                    timeRemaining--;
                } else {
                    timer.stop();
                    endTest();
                }
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();

        l.setBounds(40, 30, 400, 40);
        jb[0].setBounds(50, 80, 350, 20);
        jb[1].setBounds(50, 110, 350, 20);
        jb[2].setBounds(50, 140, 350, 20);
        jb[3].setBounds(50, 170, 350, 20);
        b1.setBounds(100, 240, 100, 30);
        b2.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Saving user's answer
            saveAnswer();

            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 19) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Bookmark")) {
            JButton bk = new JButton("Bookmark" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 19)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Bookmark" + y)) {
                // Saving user's answer
                saveAnswer();

                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }

        if (e.getActionCommand().equals("Result")) {
            // Saving user's answer for the last question
            saveAnswer();

            if (check())
                count = count + 1;
            timer.stop();
            endTest();
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText(" Soal1 : Rangka berfungsi untuk?");
            jb[0].setText("Mendukung tubuh");
            jb[1].setText("Memproduksi enzim");
            jb[2].setText("Menghasilkan darah");
            jb[3].setText("Menyaring limbah");
        }
        if (current == 1) {
            l.setText(" Soal2 : Otot yang bekerja tanpa sadar dan terdapat di organ-organ dalam tubuh disebut:?");
            jb[0].setText("Otot polos");
            jb[1].setText("Otot rangka");
            jb[2].setText("Otot jantung");
            jb[3].setText("Otot lurik");
        }
        if (current == 2) {
            l.setText(" Soal3 : Apa fungsi sendi dalam tubuh kita?");
            jb[0].setText("Mendukung tubuh");
            jb[1].setText("Memproduksi darah");
            jb[2].setText("Menghubungkan tulang dan otot");
            jb[3].setText("Menyaring limbah");
        }
        if (current == 3) {
            l.setText(" Soal 4 : Bagian tubuh yang merupakan pusat pengendalian dan koordinasi gerakan disebut:?");
            jb[0].setText("Otot");
            jb[1].setText("Sendi");
            jb[2].setText("Otak");
            jb[3].setText("Tulang");
        }
        if (current == 4) {
            l.setText(" Soal 5 : Fungsi utama sistem saraf adalah:?");
            jb[0].setText("Mendukung tubuh");
            jb[1].setText("Menyaring limbah");
            jb[2].setText("Mengontrol dan mengkoordinasikan aktivitas tubuh");
            jb[3].setText("Memproduksi enzim");
        }
        if (current == 5) {
            l.setText(" Soal 6 : Bagaimana impuls saraf dikirim dari satu neuron ke neuron lainnya?");
            jb[0].setText("Melalui peredarah darah");
            jb[1].setText("Melalui sinyal listrik");
            jb[2].setText("Melalui hormon");
            jb[3].setText("Melalui cairan limfa");
        }
        if (current == 6) {
            l.setText(" Soal 7 : Penyakit yang menyerang sistem gerak dan ditandai dengan peradangan pada sendi disebut:? ");
            jb[0].setText("Diabetes");
            jb[1].setText("Osteoporosis");
            jb[2].setText("Arthritis");
            jb[3].setText("Hipertensi");
        }
        if (current == 7) {
            l.setText(" Soal 8 : Apa yang membedakan otot rangka dengan otot polos ?");
            jb[0].setText("Lokasinya dalam tubuh");
            jb[1].setText("Kemampuan dikontrol secara sadar");
            jb[2].setText("Bentuknya");
            jb[3].setText("Kecepatan kontraksinya");
        }
        if (current == 8) {
            l.setText(" Soal 9 : Bagaimana tulang dihubungkan satu sama lain dalam tubuh kita??");
            jb[0].setText("Melalui otot");
            jb[1].setText("Melalui sendi");
            jb[2].setText("Melalui pembuluh darah");
            jb[3].setText("Melalui saraf");
        }
        if (current == 9) {
            l.setText(" Soal 10 : Otot apa yang terdapat di jantung dan bekerja tanpa kehendak kita ?");
            jb[0].setText("Otot polos");
            jb[1].setText("Otot rangka");
            jb[2].setText("Otot jangtung");
            jb[3].setText("Otot lurik");
        }

        if (current == 10) {
            l.setText(" Soal 11 : Apa yang menyebabkan musim di Bumi?");
            jb[0].setText("Gerak rotasi Bumi");
            jb[1].setText("Gerak revolusi Bumi");
            jb[2].setText("Inklinasi sumbu Bumi");
            jb[3].setText("Aktivitas manusia");
        }
        if (current == 11) {
            l.setText(" Soal 12 : Berapa waktu yang dibutuhkan Bumi untuk melakukan satu putaran penuh pada sumbunya (gerak rotasi)?");
            jb[0].setText("24 jam");
            jb[1].setText("365 hari");
            jb[2].setText("1 bulan");
            jb[3].setText("12 jam");
        }
        if (current == 12) {
            l.setText(" Soal 13 : Apa yang menyebabkan siang dan malam di Bumi?");
            jb[0].setText("Gerak rotasi Bumi");
            jb[1].setText("Gerak revolusi Bumi");
            jb[2].setText("Gravitasi Matahari");
            jb[3].setText("Kecepatan rotasi Matahari");
        }
        if (current == 13) {
            l.setText(" Soal 14 : Apa yang menyebabkan perbedaan suhu di berbagai wilayah Bumi?");
            jb[0].setText("jarak dari Matahari");
            jb[1].setText("Inklinasi sumbu Bumi");
            jb[2].setText("Bentuk topografi");
            jb[3].setText("Gas atmosfer");
        }
        if (current == 14) {
            l.setText(" Soal 15 : Apa yang disebut dengan gerak mengelilingi Matahari oleh planet?");
            jb[0].setText("Gerak rotasi");
            jb[1].setText("Gerak revolusi ");
            jb[2].setText("Gerak transilasi");
            jb[3].setText("Gerak elips");
        }
        if (current == 15) {
            l.setText(" Soal 16 : Berapa waktu yang dibutuhkan Bumi untuk melakukan satu putaran mengelilingi Matahari (gerak revolusi)?");
            jb[0].setText("24 jam");
            jb[1].setText("365 Hari");
            jb[2].setText("1 bulan");
            jb[3].setText("12 jam");
        }
        if (current == 16) {
            l.setText(" Soal 17 :Apa yang menyebabkan musim di Bumi?");
            jb[0].setText("Gerak rotasi Bumi");
            jb[1].setText("Gerak revolusi Bumi");
            jb[2].setText("Inklinasi sumbu Bumi");
            jb[3].setText("Aktivitas manusia");
        }
        if (current == 17) {
            l.setText(" Soal 18 :Bagaimana fase Bulan dapat dilihat dari Bumi?");
            jb[0].setText("Berdasarkan rotasi Bulan");
            jb[1].setText("Berdasarkan revolusi Bulan");
            jb[2].setText("Berdasarkan perubahan bentuk Bulan");
            jb[3].setText("Berdasarkan rotasi Matahari");
        }
        if (current == 18) {
            l.setText(" Soal 19 : Apa yang menyebabkan gerhana Matahari?");
            jb[0].setText("Bulan berada di anatara Bumi dan Matahari");
            jb[1].setText("Bumi berasa di antara Bulan dan Matahari");
            jb[2].setText("Bumi,Bulan, dan Matahari berada dalam satu garis lurus");
            jb[3].setText("Inklinasi sumbu Bumi");
        }
        if (current == 19) {
            l.setText(" Soal 20 : planet terdekat dengan Matahari di dalam Sistem Surya?");
            jb[0].setText("Venus");
            jb[1].setText("Mars");
            jb[2].setText("Jupiter");
            jb[3].setText("Merkurius");
        }

        l.setBounds(40,40,2050,40);
        for(int i=0,j=0;i<=90;i+=30,j++)
            jb[j].setBounds(50,80+i,350,20);

        // Set other questions...
    }

    boolean check() {
        if (current == 0)
            return (jb[0].isSelected());
        // Check other questions...
        if (current == 1)
            return (jb[0].isSelected());
        if (current == 2)
            return (jb[2].isSelected());
        if (current == 3)
            return (jb[2].isSelected());
        if (current == 4)
            return (jb[2].isSelected());
        if (current == 5)
            return (jb[1].isSelected());
        if (current == 6)
            return (jb[2].isSelected());
        if (current == 7)
            return (jb[1].isSelected());
        if (current == 8)
            return (jb[1].isSelected());
        if (current == 9)
            return (jb[2].isSelected());
        if (current == 10)
            return (jb[2].isSelected());
        if (current == 11)
            return (jb[0].isSelected());
        if (current == 12)
            return (jb[0].isSelected());
        if (current == 13)
            return (jb[0].isSelected());
        if (current == 14)
            return (jb[1].isSelected());
        if (current == 15)
            return (jb[1].isSelected());
        if (current == 16)
            return (jb[2].isSelected());
        if (current == 17)
            return (jb[2].isSelected());
        if (current == 18)
            return (jb[0].isSelected());
        if (current == 19)
            return (jb[3].isSelected());
        return false;
    }

    // Save user's answer for the current question
    void saveAnswer() {
        if (current >= 0 && current < 20) {
            if (jb[0].isSelected()) {
                userAnswers[current] = 'A';
            } else if (jb[1].isSelected()) {
                userAnswers[current] = 'B';
            } else if (jb[2].isSelected()) {
                userAnswers[current] = 'C';
            } else if (jb[3].isSelected()) {
                userAnswers[current] = 'D';
            } else {
                userAnswers[current] = ' '; // No answer selected
            }
        }
    }

    // Display result and correct answers
    void endTest() {
        int totalQuestions = 20;
        int score = count;
        StringBuilder message = new StringBuilder("Total benar = ").append(score).append("\n");
        message.append("Poin yang didapatkan = ").append(score * 5).append("\n\n");
        message.append("Jawaban yang benar:\n");
        // Correct answers
        char[] correctAnswers = {'A', 'A', 'C', 'C', 'C', 'B', 'C', 'B', 'B', 'C', 'C', 'A', 'A', 'A', 'B', 'B', 'C', 'C', 'A', 'D'};
        // Display user's answers and correct answers
        for (int i = 0; i < totalQuestions; i++) {
            message.append("Soal ").append(i + 1).append(": ");
            message.append("Jawaban Anda: ").append(userAnswers[i]).append(", Jawaban Benar: ").append(correctAnswers[i]);
            message.append("\n");
        }
        JOptionPane.showMessageDialog(this, message.toString());
        System.exit(0);
    }

    public static void main(String s[]) {
        new OnlineTest("Online Test Of Java 2200018178_Shah firizki Azmi");
    }
}