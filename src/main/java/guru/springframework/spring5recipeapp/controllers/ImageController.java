package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.ImageService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showImageForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String save(@PathVariable String recipeId, @RequestParam("imagefile")MultipartFile file) {
        imageService.save(Long.valueOf(recipeId), file);
        return "redirect:/recipe/view/" + recipeId;
    }

    @GetMapping("recipe/{recipeId}/recipeimage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(recipeId));
        if (command.getImage() != null) {
            try {
                byte[] bytes = new byte[command.getImage().length];
                int i = 0;
                for (Byte wrappedByte : command.getImage()) {
                    bytes[i++] = wrappedByte;
                }

                response.setContentType("image/jpeg");
                InputStream inputStream = new ByteArrayInputStream(bytes);

                IOUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
