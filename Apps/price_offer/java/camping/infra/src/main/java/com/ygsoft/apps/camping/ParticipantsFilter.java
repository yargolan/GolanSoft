package com.ygsoft.apps.camping;

import java.util.List;
import java.util.ArrayList;




public class ParticipantsFilter {


    public static List<Participant> filter(
            List<Participant> originalList,
            List<Participant> alreadyListed) {

        for (Participant alreadyListedParticipant : alreadyListed) {
            String initials = alreadyListedParticipant.getInitials();
            originalList.removeIf(n -> (n.getInitials().equals(initials)));
        }

        return originalList;
    }
}





