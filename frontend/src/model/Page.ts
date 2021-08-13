export default interface Page<Type> {
    content: Array<Type>,
    totalPages: number,
    totalElements: number,
    numberOfElements: number,
    size: number,
    number: number
}