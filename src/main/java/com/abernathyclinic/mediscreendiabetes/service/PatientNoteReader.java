package com.abernathyclinic.mediscreendiabetes.service;

import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientNoteReader {

    public List<String> NoteReader(List<PatientNote> patientNoteList) {
        List<String> keyWordList = new ArrayList<>(Arrays.asList("hemoglobine a1c","microalbumine","taille","poids","fumeur","anormal","cholesterol","vertige","rechute","reaction","anticorps"));
        return keyWordList.stream()
                .filter(word -> patientNoteList.stream()
                        .map(note -> note.getNotes().toLowerCase()).collect(Collectors.toList())
                        .contains(word.toLowerCase()))
                .collect(Collectors.toList());
    }
}
