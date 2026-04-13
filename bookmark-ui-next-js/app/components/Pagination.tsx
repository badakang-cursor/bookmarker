import React from 'react'
import { BookmarksResponse } from '../types/bookmark'
import Link from 'next/link';

interface PaginationProps{
    bookmarks: BookmarksResponse;
    query ?: string;
}

const Pagination = ({bookmarks, query}: PaginationProps) => {
    const path = "/bookmarks";
    const queryParams = (query === undefined || query === '')? {}:{query:query};

    // const firstPage = {pathname:path, query:{page:1}};
    // const nextPage = {pathname:path, query:{page:bookmarks.currentPage+1}}
    const previousPage = {pathname:path, query:{page:bookmarks.currentPage-1, ...queryParams}}
    const lastPage = {pathname:path, query:{page:bookmarks.totalPage, ...queryParams}}
  return (
    <div>
        <nav aria-label="Page navigation">
        <ul style={{display:'flex', justifyContent:'space-between', padding:'0', listStyle:'none'}} className="pagination">
            <li className={"page-item"+ (bookmarks.hasPrevious ? "":"-disable")}><Link className="page-link" href={previousPage}>Previous</Link></li>
            <li className={"page-item"+ (bookmarks.hasNext  ? "":"-disable")}><Link className="page-link" href={lastPage}>Next</Link></li>
        </ul>
        </nav>
    </div>
  )
}

export default Pagination
