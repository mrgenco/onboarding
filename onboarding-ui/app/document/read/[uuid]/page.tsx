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
            <div className="relative w-full px-6 py-12 bg-white shadow-xl shadow-slate-700/10 ring-1 ring-gray-900/5 md:max-w-3xl md:mx-auto lg:max-w-4xl lg:pt-16 lg:pb-28">
                <h2 className="text-2xl font-bold mb-8">About this document</h2>
                <h3>Last updated : {document.lastUpdatedTime} </h3>
                <h3>Written by : </h3>
            </div>
            <br />
            <div className="relative w-full px-6 py-12 bg-white shadow-xl shadow-slate-700/10 ring-1 ring-gray-900/5 md:max-w-3xl md:mx-auto lg:max-w-4xl lg:pt-16 lg:pb-28">
                <article className="prose lg:prose-xl" dangerouslySetInnerHTML={{__html:document.content}}/>
            </div>
        </div>
    )
}