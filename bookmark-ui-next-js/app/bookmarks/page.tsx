import { fetchBookmarks } from '@/services/api/fetchBookmarks';
import React from 'react'
import Bookmarks from '../components/Bookmarks';
import Pagination from '../components/Pagination';
import SearchForm from '../components/SearchForm';

//가상데이터를 페칭

const page = async ({searchParams}: {searchParams: Promise<{ page ?: string, query ?: string}>}) => {
  const {page, query} = await searchParams;
  const pageNumber = page ? parseInt(page, 10):1;
  const queryString = query ? String(query) : "";
  const bookmarks = await fetchBookmarks(pageNumber, queryString);

  return (
    <div>
      <h2>Welcome to bookmarks</h2>
      <SearchForm/>
      <Pagination bookmarks={bookmarks} query={query} />
      <ul>
        <Bookmarks bookmarks={bookmarks}/>
      </ul>
    </div>
  )
}

export default page

