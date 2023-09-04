package com.example.sop_lab_5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    Word words = new Word();

    @RequestMapping(path = "addBad/{word}")
    public ArrayList addBadWord(@PathVariable("word") String s) {
        if (!this.words.badWords.contains(s)) {
            this.words.badWords.add(s);
        }
        return this.words.badWords;
    }

    @RequestMapping(path = "delBad/{word}")
    public ArrayList deleteBadWord(@PathVariable("word") String s) {
        this.words.badWords.remove(s);
        return this.words.badWords;
    }

    @RequestMapping(path = "addGood/{word}")
    public ArrayList addGoodWord(@PathVariable("word") String s) {
        if (!this.words.goodWords.contains(s)) {
            this.words.goodWords.add(s);
        }
        return this.words.goodWords;
    }

    @RequestMapping(path = "delGood/{word}")
    public ArrayList deleteGoodWord(@PathVariable("word") String s) {
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }

    @RequestMapping(path = "proof/{sentence}")
    public String proofSentence(@PathVariable("sentence") String s) {
        boolean good = false, bad = false;
        for (String word : this.words.badWords) {
            if (s.contains(word)) {
                bad = true;
            }
        }
        for (String word : this.words.goodWords) {
            if (s.contains(word)) {
                good = true;
            }
        }
        if (good && bad) {
            rabbitTemplate.convertAndSend("Fanout", "", s);
            return "Found Bad & Good Word";
        } else if (good) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            return "Found Good Word";
        }
        rabbitTemplate.convertAndSend("Direct", "bad", s);
        return "Found Bad Word";
    }

    @RequestMapping(path = "getSentence", method = RequestMethod.GET)
    public Sentence getSentence() {
        Object output = rabbitTemplate.convertSendAndReceive("RPCDirectExchange", "rpctest", "");
        return (Sentence) output;
    }
}
