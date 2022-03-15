package com.fiejdasz.solver.exceptions;

public class InfiniteRootsException extends SolverException{
    public InfiniteRootsException() {
        super("Rownanie ma nieskończenie wiele rozwiązań");
    }
}
