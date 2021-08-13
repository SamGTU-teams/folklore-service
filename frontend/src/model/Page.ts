export interface Page<T>{
    content: Array<T>,
    totalPages: number,
    totalElements: number,
    numberOfElements: number,
    size: number,
    number: number
}