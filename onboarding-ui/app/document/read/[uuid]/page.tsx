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
}

export default async function Page({ params }: { params: { uuid: string } }) {
    
    const document: DocumentResponse = await renderDocument(params.uuid);


    return (
        <div>
            <h1>Test Tailwind</h1>
            <article className="prose lg:prose-xl"  >
                {document.content}
            </article> 
        </div>
    )
}