package com.visualizer.controller;

import com.visualizer.model.StackData;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stack")
public class StackController {
    private StackData stack;

    @PostMapping("/init/{size}")
    public String init(@PathVariable int size) {
        stack = new StackData(size);
        return "Stack initialized with size " + size;
    }

    @PostMapping("/push/{value}")
    public Map<String, Object> push(@PathVariable int value) {
        if (stack == null) throw new RuntimeException("Stack not initialized");
        stack.push(value);
        return getState();
    }

    @DeleteMapping("/pop")
    public Map<String, Object> pop() {
        if (stack == null) throw new RuntimeException("Stack not initialized");
        int popped = stack.pop();
        Map<String, Object> response = getState();
        response.put("popped", popped);
        return response;
    }

    @GetMapping("/peek")
    public Map<String, Object> peek() {
        if (stack == null) throw new RuntimeException("Stack not initialized");
        Map<String, Object> response = new HashMap<>();
        response.put("peeked", stack.peek());
        return response;
    }

    @GetMapping("/state")
    public Map<String, Object> getState() {
        if (stack == null) {
            Map<String, Object> empty = new HashMap<>();
            empty.put("initialized", false);
            return empty;
        }
        Map<String, Object> state = new HashMap<>();
        state.put("elements", stack.getElements());
        state.put("capacity", stack.getCapacity());
        state.put("top", stack.getTop());
        state.put("isEmpty", stack.isEmpty());
        state.put("isFull", stack.isFull());
        state.put("initialized", true);
        return state;
    }
}
