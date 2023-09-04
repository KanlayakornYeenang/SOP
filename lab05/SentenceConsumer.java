package com.example.sop_lab_5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer {
    Sentence sentences = new Sentence();

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s) {
        this.sentences.badSentences.add(s);
        System.out.println("In addBadSentence Method : " + this.sentences.badSentences);
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s) {
        this.sentences.goodSentences.add(s);
        System.out.println("In addGoodSentence Method : " + this.sentences.goodSentences);
    }

    @RabbitListener(queues = "GetQueue")
    public Sentence getSentences() {
        System.out.println("In getSentences Method");
        return sentences;
    }
}
