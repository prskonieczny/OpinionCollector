import { useCategory } from '../hooks/useCategory';
import { useState, useEffect, useCallback } from 'react';
import { useProduct, useProductOrigins } from '../hooks/useProductOrigins';
import { putProduct, getProductById, getWholeProductById } from '../api/productApi';
import { EditProductForm } from '../modules/product-details/components/EditProductForm/EditProductForm';
import css from './EditProductDetails.module.scss';
import CopyrightFooter from '../common/layouts/components/CopyrightFooter/CopyrightFooter';
import BgAsset from '../common/images/bg_asset.png';
import { useNavigate, useParams } from 'react-router-dom';
import { PageLoad } from './PageLoad';
import { getCategory } from '../api/categoryApi';
import { useClient } from '../hooks/useUser';

export function EditProductDetails() {
  const navigate = useNavigate();

  const { id } = useParams();
  const { client } = useClient();
  const { origins, loading } = useProductOrigins();
  const { categories, loadingCat } = useCategory();

  const [product, setProduct] = useState(null);
  const [category, setCategory] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [isFilePicked, setIsFilePicked] = useState(false);
  const [title, setTitle] = useState('');
  const [origin, setOrigin] = useState('');
  const [ean, setEan] = useState('');

  const fetchProductData = useCallback(async () => {
    const response = await getWholeProductById(id);
    setProduct(response[0]);
  }, [id]);

  const categoryData = useCallback( async (categoryId) => {
     const response = await getCategory(categoryId);
     setCategory(response[0]);
  }, []);

  useEffect(() => {
    fetchProductData();
  }, [fetchProductData]);

  useEffect(() => {
    if (product) {
      categoryData(product.categoryId);
    }
  }, [categoryData, product])

  useEffect(() => {
    if (product !== null) {
      setSelectedFile(product.image);
      setTitle(product.title);
      setOrigin(product.origin);
      setEan(product.ean);
    }
  }, [product]);

  if (product === null || category === null) {
    return <PageLoad />;
  }
  if (loading) {
    return <p>loading origins...</p>;
  }
  if (loadingCat) {
    return <p>loading categories...</p>;
  }

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
    setIsFilePicked(true);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData();
    if (selectedFile) {
      formData.append('image', selectedFile);
    }
    formData.append('categoryId', category.categoryId);
    formData.append('id', id);
    formData.append('title', title);
    formData.append('origin', origin);
    formData.append('ean', ean);
    navigate('/products');
    console.log(Object.fromEntries(formData));
    await putProduct(formData);
  };

  return (
    <div className={css.container}>
      <div className={css.title}>
        <h2>Edit a product</h2>
      </div>
      <EditProductForm
        className={css.productForm}
        handleSubmit={handleSubmit}
        handleFileChange={handleFileChange}
        isFilePicked={isFilePicked}
        categories={categories}
        origins={origins}
        selectedFile={selectedFile}
        setEan={setEan}
        setCategory={setCategory}
        setOrigin={setOrigin}
        setTitle={setTitle}
        ean={ean}
        category={category}
        origin={origin}
        title={title}
        id={id}
      />
      <div className={css.bgImg}>
        <img src={BgAsset} className={css.bgAsset} alt="Fajne zdjęcie" />
      </div>
      <CopyrightFooter />
    </div>
  );
}
