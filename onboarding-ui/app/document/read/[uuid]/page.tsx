import ReactMarkDown from 'react-markdown'

async function renderDocument(uuid : string) {
    const res = await fetch('http://localhost:8080/document/html/' + uuid, { cache: 'no-store' })
    console.log(uuid);
    if (!res.ok) {
        throw new Error('Failed to fetch document information')
    }
    return res.json()
}
interface DocumentResponse {
    content: string;
    title: string;
    lastUpdatedTime : string;
}

export default async function Page({ params }: { params: { uuid: string} }) {
    
    const document: DocumentResponse = await renderDocument(params.uuid);


    return (
        <div className='text-black'>
            <h1 className="text-4xl font-bold mb-8">{document.title}</h1>
            <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal">
                <h2 className="text-2xl font-bold mb-8">About this document</h2>
                <h3>Last updated : {document.lastUpdatedTime} </h3>
                <h3>Written by : </h3>
            </div>
            <br />
            <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-center leading-normal">
                <article className="prose lg:prose-xl" dangerouslySetInnerHTML={{__html:document.content}}/>
            </div>
        </div>
    )
}