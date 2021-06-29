package com.example.selection.data_models;

import java.util.ArrayList;

public class Questions {
    public static ArrayList<AbstractQuestion> questions=new ArrayList<AbstractQuestion>();
    public static void init(){
        MultiQuestionMultiChoices question1=new MultiQuestionMultiChoices();
        question1.setQuestionDescription("Trong các mô hình mạng dưới đây, mô hình nào được dùng phổ biến hiện nay:");
        question1.setQuestionChoices("Peer - to – Peer","Remote Access","Terminal Mainframe","Client – Server");
        question1.setQuestionCorrect(0,3);
        questions.add(question1);

        MultiQuestionOneChoice question2=new MultiQuestionOneChoice();
        question2.setQuestionDescription("Dịch vụ mạng DNS dùng để làm gì?");
        question2.setQuestionChoices("Cấp địa chỉ cho các máy trạm","Phân giải tên miền và địa chỉ IP","Truyền file và dữ liệu","Gửi thư điện tử");
        question2.setQuestionCorrect(1);
        questions.add(question2);

        MatchingQuestion question3=new MatchingQuestion();
        question3.setQuestionDescription("Hãy tìm những đáp án đúng cho các mệnh đề dưới đây bằng cách nhấp chọn ô xổ xuống bên tay phải và lựa chọn câu trả lời:");
        question3.setQuestionChoiceA("Giao thức DHCP có thể cấp được...","Mô hình mạng dùng nhiều nhất...","Dịch vụ DNS dùng để ...");
        question3.setQuestionChoiceB("Địa chỉ Mac","Địa chỉ IP","Subnet Mask","Client – Server","Phân giải tên và địa chỉ");
        question3.setQuestionCorrect(1,3,4);
        questions.add(question3);

        TrueFalseQuestion question4=new TrueFalseQuestion();
        question4.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách nhấp chuột vào nút bên tay phải:");
        question4.setQuestionChoices("Giao thức DHCP có thể cấp được địa chỉ IP","Mô hình mạng phổ biến: Terminal – Mainframe","Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question4.setQuestionCorrect(1,0,1);
        questions.add(question4);

        TrueFalseQuestion question5=new TrueFalseQuestion();
        question5.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách kéo thanh trượt bên tay phải");
        question5.setQuestionChoices("Mô hình mạng phổ biến: Terminal – Mainframe","Giao thức DHCP có thể cấp được địa chỉ IP","Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question5.setQuestionCorrect(0,1,1);
        questions.add(question5);

        MultiQuestionMultiChoices question6=new MultiQuestionMultiChoices();
        question6.setQuestionDescription("Trong các mô hình mạng dưới đây, mô hình nào được dùng phổ biến hiện nay:");
        question6.setQuestionChoices("Peer - to – Peer","Remote Access","Terminal Mainframe","Client – Server");
        question6.setQuestionCorrect(0,3);
        questions.add(question6);

        MultiQuestionOneChoice question7=new MultiQuestionOneChoice();
        question7.setQuestionDescription("Dịch vụ mạng DNS dùng để làm gì?");
        question7.setQuestionChoices("Cấp địa chỉ cho các máy trạm","Phân giải tên miền và địa chỉ IP","Truyền file và dữ liệu","Gửi thư điện tử");
        question7.setQuestionCorrect(1);
        questions.add(question7);

        MatchingQuestion question8=new MatchingQuestion();
        question8.setQuestionDescription("Hãy tìm những đáp án đúng cho các mệnh đề dưới đây bằng cách nhấp chọn ô xổ xuống bên tay phải và lựa chọn câu trả lời:");
        question8.setQuestionChoiceA("Giao thức DHCP có thể cấp được...","Mô hình mạng dùng nhiều nhất...","Dịch vụ DNS dùng để ...");
        question8.setQuestionChoiceB("Địa chỉ Mac","Địa chỉ IP","Subnet Mask","Client – Server","Phân giải tên và địa chỉ");
        question8.setQuestionCorrect(1,3,4);
        questions.add(question8);

        TrueFalseQuestion question9=new TrueFalseQuestion();
        question9.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách nhấp chuột vào nút bên tay phải:");
        question9.setQuestionChoices("Giao thức DHCP có thể cấp được địa chỉ IP","Mô hình mạng phổ biến: Terminal – Mainframe","Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question9.setQuestionCorrect(1,0,1);
        questions.add(question9);

        TrueFalseQuestion question10=new TrueFalseQuestion();
        question10.setQuestionDescription("Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách kéo thanh trượt bên tay phải");
        question10.setQuestionChoices("Mô hình mạng phổ biến: Terminal – Mainframe","Giao thức DHCP có thể cấp được địa chỉ IP","Dịch vụ DNS dùng để phân giải tên và địa chỉ");
        question10.setQuestionCorrect(0,1,1);
        questions.add(question10);
    }
}
