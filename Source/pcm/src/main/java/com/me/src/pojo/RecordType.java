package com.me.src.pojo;

public enum RecordType {
	General { @Override public String toString() { return "General"; } },
	Respiratory { @Override public String toString() { return "Respiratory"; } },
	Oral { @Override public String toString() { return "Oral"; } },
	Gastritis { @Override public String toString() { return "Gastritis"; } },
	Skin { @Override public String toString() { return "Skin"; } },
	Orthopedic { @Override public String toString() { return "Orthopedic"; } },
	Cognitive { @Override public String toString() { return "Cognitive"; } },
	Neurological { @Override public String toString() { return "Neurological"; } },
	EyeNoseEar { @Override public String toString() { return "Eye Nose Ear"; } },
	Immunization{ @Override public String toString() { return "Immunization"; } }	
}
