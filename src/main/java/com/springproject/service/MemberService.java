package com.springproject.service;


import com.springproject.DTO.LoginDTO;
import com.springproject.DTO.RegisterDTO;
import com.springproject.DTO.UpdateDTO;
import com.springproject.domain.Members;
import com.springproject.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MembersRepository memberrepository;

    public String saveProfileImage(MultipartFile profileImage) {
        try {
            // 문건우용   String imageFolder = "/Users/moongeonu/SpringProject/src/main/resources/static/assets/images";
            // 윈도우용
            String imageFolder = "C:/SpringProject/src/main/resources/static/assets/images";

            String originalFilename = profileImage.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = System.currentTimeMillis() + fileExtension;

            Path targetPath = Paths.get(imageFolder, uniqueFilename);
            File targetFile = targetPath.toFile();
            profileImage.transferTo(targetFile);

            return "/assets/images/" + uniqueFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Transactional
    public void updateMember(Members member, UpdateDTO form, HttpSession session) {
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        String newPassword = form.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            member.setPassword(newPassword);
        }
        member.setGithubLink(form.getGithublink());

        MultipartFile profileImage = form.getProfileimage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String imagePath = saveProfileImage(profileImage);
            member.setImage(imagePath);
            Members sessionMember = (Members) session.getAttribute("Member");
            sessionMember.setImage(imagePath);
            session.setAttribute("Member", sessionMember);
        }

        memberrepository.update(member);
    }


    @Transactional
    public void deleteMember(Members member) {
        memberrepository.remove(member);
    }

    @Transactional
    public void register(Members member){
        memberrepository.save(member);
    }

    public Members login(String id, String pw){
        Members requestMember = memberrepository.findByID(id);

        if(requestMember == null){
            return null;
        }else if(!requestMember.getPassword().equals(pw)){
            return null;
        }else{
            return requestMember;
        }
    }

    public Members findByID(String id){
        Members foundMember = memberrepository.findByID(id);

        return foundMember;
    }

    public Members findMemberById(Long memberId) {
        return memberrepository.findById(memberId).orElse(null);
    }
}
