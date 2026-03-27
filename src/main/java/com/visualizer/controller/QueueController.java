package com.visualizer.controller;

import com.visualizer.model.QueueData;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/queue")
public class QueueController {
    private QueueData queue;

    @PostMapping("/init/{size}")
    public String init(@PathVariable int size) {
        queue = new QueueData(size);
        return "Queue initialized with size " + size;
    }

    @PostMapping("/enqueue/{value}")
    public Map<String, Object> enqueue(@PathVariable int value) {
        if (queue == null) throw new RuntimeException("Queue not initialized");
        queue.enqueue(value);
        return getState();
    }

    @DeleteMapping("/dequeue")
    public Map<String, Object> dequeue() {
        if (queue == null) throw new RuntimeException("Queue not initialized");
        int dequeued = queue.dequeue();
        Map<String, Object> response = getState();
        response.put("dequeued", dequeued);
        return response;
    }

    @GetMapping("/peek")
    public Map<String, Object> peek() {
        if (queue == null) throw new RuntimeException("Queue not initialized");
        Map<String, Object> response = new HashMap<>();
        response.put("peeked", queue.peek());
        return response;
    }

    @GetMapping("/state")
    public Map<String, Object> getState() {
        if (queue == null) {
            Map<String, Object> empty = new HashMap<>();
            empty.put("initialized", false);
            return empty;
        }
        Map<String, Object> state = new HashMap<>();
        state.put("elements", queue.getElements());
        state.put("rawArray", queue.getRawArray());
        state.put("capacity", queue.getCapacity());
        state.put("size", queue.getSize());
        state.put("isEmpty", queue.isEmpty());
        state.put("isFull", queue.isFull());
        state.put("initialized", true);
        return state;
    }
}
