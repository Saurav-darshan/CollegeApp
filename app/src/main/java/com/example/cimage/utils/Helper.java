package com.example.cimage.utils;

import com.example.cimage.R;
import com.example.cimage.models.EducationModel;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static List<EducationModel> getEducationList(){
        List<EducationModel> list = new ArrayList<>();
        list.add(new EducationModel("Education","Being a Top BCA college in Patna, Bihar, CIMAGE offers state-of-the-art and skill-based education. We focused on the overall development of students to make them skilled. We are equipped with Hi-Tech audio-visual airconditioned classrooms, informative library and IT Labs. ", R.drawable.ic_exam));
        list.add(new EducationModel("Result","Every year CIMAGians are receiving Gold Medal for University Topper in their course. Because of good academic results, it is popularly known as the best BCA college in Patna, Bihar. All across Bihar CIMAGE is one of the top colleges for BCA, BBA, and other IT-Management courses ",R.drawable.ic_result));
        list.add(new EducationModel("Placement","12000+ Students are placed in 210+ companies including top MNCs. Over the years CIMAGE Group of Institutions has excellent campus placement records. Best BCA college in Patna, Bihar offers highest campus Placement Facility",R.drawable.ic_holiday));

        return  list;
    }

    public static List<String> getAmenities(){
        List<String> list = new ArrayList<>();
        list.add("World Class Infrastructure");
        list.add("Hi-Tech Digital Library ");
        list.add("Ranked No.1 College in Bihar by Times of India");
        list.add("Best B-School of India East by ASSOCHAM");
        list.add("Modern Facility Computer Labs");
        list.add("Triple mode education (Classroom+Zoom+Youtube)");
        list.add("Job Oriented Trainings with IIT Collaboration");
        list.add("'Most Emerging Institute for Management Education Award");
        list.add("Top BCA College in Patna, Bihar");
        return list;
    }
}
