package com.elkin.pruebaTecnica.mapper;

public interface IMapper<I, O> {
    O map(I in);
}
